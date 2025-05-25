package com.tripm.profileservice.mapper;

import com.tripm.profileservice.dto.request.UpdateProfileRequest;
import com.tripm.profileservice.dto.request.UserProfileCreationRequest;
import com.tripm.profileservice.dto.response.UserProfileResponse;
import com.tripm.profileservice.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    UserProfile toUserProfile(UserProfileCreationRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile entity);

    void update(@MappingTarget UserProfile entity, UpdateProfileRequest request);
}
