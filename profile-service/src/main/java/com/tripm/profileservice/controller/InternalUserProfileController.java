package com.tripm.profileservice.controller;

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
    public UserProfileResponse createUserProfile(@RequestBody UserProfileCreationRequest userProfileCreationRequest) {
        return userProfileService.createUserProfile(userProfileCreationRequest);
    }
}
