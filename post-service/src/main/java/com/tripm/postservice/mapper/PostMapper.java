package com.tripm.postservice.mapper;

import com.tripm.postservice.dto.request.PostRequest;
import com.tripm.postservice.dto.response.PostResponse;
import com.tripm.postservice.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostResponse toPostResponse(Post post);
}
