package com.tripm.profileservice.controller;

import com.tripm.profileservice.dto.ApiResponse;
import com.tripm.profileservice.dto.request.UserProfileCreationRequest;
import com.tripm.profileservice.dto.response.UserProfileResponse;
import com.tripm.profileservice.service.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {

    UserProfileService userProfileService;

    @PostMapping("/internal/users")
    public ApiResponse<UserProfileResponse> createUserProfile(@RequestBody UserProfileCreationRequest request) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.createUserProfile(request))
                .build();
    }

    @GetMapping("/internal/users/{userId}")
    public ApiResponse<UserProfileResponse> getUserProfile(@PathVariable String userId) {
        return ApiResponse.<UserProfileResponse>builder()
                .result(userProfileService.getByUserId(userId))
                .build();
    }
}
