package com.tripm.apigateway.service;

import com.tripm.apigateway.dto.ApiResponse;
import com.tripm.apigateway.dto.request.IntrospectRequest;
import com.tripm.apigateway.dto.response.IntrospectResponse;
import com.tripm.apigateway.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {

    IdentityClient identityClient;

    //TODO: Delegate identity service to validate token
    public Mono<ApiResponse<IntrospectResponse>> introspect(String token){
        return identityClient.introspect(IntrospectRequest.builder()
                        .token(token)
                .build());
    }
}
