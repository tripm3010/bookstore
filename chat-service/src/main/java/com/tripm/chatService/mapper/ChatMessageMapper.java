package com.tripm.chatService.mapper;

import com.tripm.chatService.dto.request.ChatMessageRequest;
import com.tripm.chatService.dto.response.ChatMessageResponse;
import com.tripm.chatService.entity.ChatMessage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChatMessageMapper {
    ChatMessageResponse toChatMessageResponse(ChatMessage chatMessage);

    ChatMessage toChatMessage(ChatMessageRequest request);

    List<ChatMessageResponse> toChatMessageResponses(List<ChatMessage> chatMessages);
}