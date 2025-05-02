package com.tripm.profileservice.mapper;

import com.tripm.profileservice.dto.request.UserProfileCreationRequest;
import com.tripm.profileservice.dto.response.UserProfileResponse;
import com.tripm.profileservice.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toUserProfile(UserProfileCreationRequest userProfileCreationRequest);

    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
