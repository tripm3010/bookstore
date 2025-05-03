package com.tripm.apigateway.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripm.apigateway.dto.ApiResponse;
import com.tripm.apigateway.service.IdentityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationFilter implements GlobalFilter, Ordered {

    IdentityService identityService;
    ObjectMapper objectMapper;

    @NonFinal
    private String[] publicEndpoints = {"/identity/auth/.*",
            "/identity/users/registration",
            "/notification/email/send"};


    @NonFinal
    @Value("${app.api-prefix}")
    private String apiPrefix;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("AuthenticationFilter...");

        if(isPublicEndpoint(exchange.getRequest())){
            return chain.filter(exchange);
        }

        //Get token from authorization header
        List<String> authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION);

        if(CollectionUtils.isEmpty(authHeader)) {
            try {
                return unauthenticated(exchange.getResponse());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }

        String token = authHeader.get(0).replace("Bearer ", "");
        log.info("Token: {}", token);

        return identityService.introspect(token).flatMap(introspectResponseApiResponse -> {
           if(introspectResponseApiResponse.getResult().isValid()){
               return chain.filter(exchange);
           } else {
               try {
                   return unauthenticated(exchange.getResponse());
               } catch (JsonProcessingException e) {
                   throw new RuntimeException(e);
               }
           }
        }).onErrorResume(throwable -> {
            try {
                return unauthenticated(exchange.getResponse());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @Override
    public int getOrder() {
        return 0;
    }

    private boolean isPublicEndpoint(ServerHttpRequest request) {
    return Arrays.stream(publicEndpoints)
            .anyMatch(s -> request.getURI().getPath().matches(apiPrefix + s));
    }


    Mono<Void> unauthenticated(ServerHttpResponse response) throws JsonProcessingException {
        ApiResponse<?> apiResponse = ApiResponse.builder()
                .code(1401)
                .message("Unauthenticated")
                .build();
        String body = objectMapper.writeValueAsString(apiResponse);

        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(body.getBytes())));
    }
}
