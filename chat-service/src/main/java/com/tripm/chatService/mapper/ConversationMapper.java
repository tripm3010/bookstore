package com.tripm.chatService.mapper;

import com.tripm.chatService.dto.response.ConversationResponse;
import com.tripm.chatService.entity.Conversation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConversationMapper {
    ConversationResponse toConversationResponse(Conversation conversation);

    List<ConversationResponse> toConversationResponseList(List<Conversation> conversations);
}