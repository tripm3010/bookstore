package com.tripm.postservice.service;

import com.tripm.postservice.dto.PageResponse;
import com.tripm.postservice.dto.request.PostRequest;
import com.tripm.postservice.dto.response.PostResponse;
import com.tripm.postservice.entity.Post;
import com.tripm.postservice.mapper.PostMapper;
import com.tripm.postservice.repository.PostRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostService {
    PostRepository postRepository;
    PostMapper postMapper;
    DateTimeFormatter formatter;
    private final DateTimeFormatter dateTimeFormatter;

    public PostResponse createPost(PostRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        var post = Post.builder()
                .userId(authentication.getName())  // = getSub in jwt token
                .content(request.getContent())
                .createdDate(Instant.now())
                .modifiedDate(Instant.now())
                .build();

        return postMapper.toPostResponse(postRepository.save(post));
    }

    public PageResponse<PostResponse> getPosts(int page, int size){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        Pageable pageable = PageRequest.of(page - 1, size, sort);

        var pageData = postRepository.findAllByUserId(userId, pageable);

        var postList = pageData.getContent().stream().map(
                post -> {
                var postResponse = postMapper.toPostResponse(post);
                postResponse.setCreated(dateTimeFormatter.format(post.getCreatedDate()));
                return postResponse;
                }).toList();

        return PageResponse.<PostResponse>builder()
                .currentPage(page)
                .pageSize(pageData.getTotalPages())
                .totalPages(pageData.getTotalPages())
                .totalElements(pageData.getTotalElements())
                .data(postList)
                .build();
    }

}
