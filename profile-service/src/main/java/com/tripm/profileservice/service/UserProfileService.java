package com.tripm.profileservice.service;

import com.tripm.profileservice.dto.request.UserProfileCreationRequest;
import com.tripm.profileservice.dto.response.UserProfileResponse;
import com.tripm.profileservice.entity.UserProfile;
import com.tripm.profileservice.exception.AppException;
import com.tripm.profileservice.exception.ErrorCode;
import com.tripm.profileservice.mapper.UserProfileMapper;
import com.tripm.profileservice.repository.UserProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
@RequiredArgsConstructor
public class UserProfileService {

    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    public UserProfileResponse createUserProfile(UserProfileCreationRequest request) {
        var userProfile = userProfileRepository.save(userProfileMapper.toUserProfile(request));
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    public UserProfileResponse getUserProfile(String id){
        var userProfile = userProfileRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserProfileResponse> getAllProfile(){
        var profiles =  userProfileRepository.findAll();
        return profiles.stream().map(userProfileMapper::toUserProfileResponse).toList();
    }

    public UserProfileResponse getByUserId(String userId){
        UserProfile userProfile = userProfileRepository.findByUserId(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        return userProfileMapper.toUserProfileResponse(userProfile);
    }

}
