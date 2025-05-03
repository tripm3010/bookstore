package com.tripm.notificationservice.service;

import com.tripm.notificationservice.dto.request.EmailRequest;
import com.tripm.notificationservice.dto.request.SendEmailRequest;
import com.tripm.notificationservice.dto.request.Sender;
import com.tripm.notificationservice.dto.response.EmailResponse;
import com.tripm.notificationservice.exception.AppException;
import com.tripm.notificationservice.exception.ErrorCode;
import com.tripm.notificationservice.repository.httpclient.EmailClient;
import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {

    EmailClient emailClient;

    String apiKey = "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";

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .sender(Sender.builder()
                        .name("Tripm dev")
                        .email("tripm.uit@gmail.com")
                        .build())
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .htmlContent(request.getHtmlContent())
                .build();
try {
    return emailClient.sendEmail(apiKey,emailRequest);
} catch (FeignException e){
    throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
}

    }


}
