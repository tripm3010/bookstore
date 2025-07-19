package com.tripm.chatService.service;

import com.tripm.chatService.dto.request.ChatMessageRequest;
import com.tripm.chatService.dto.response.ChatMessageResponse;
import com.tripm.chatService.mapper.ChatMessageMapper;
import com.tripm.chatService.repository.ChatMessageRepository;
import com.tripm.chatService.repository.httpclient.ProfileClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatMessageService {
    ChatMessageRepository chatMessageRepository;
    ProfileClient profileClient;

    ChatMessageMapper chatMessageMapper;

    public List<ChatMessageResponse> getMessages(String conversationId) {
        return Collections.emptyList();
    }

    public ChatMessageResponse create(ChatMessageRequest request) {
        return null;
    }
}


