package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.exception.NoSuchUsernameException;
import com.gaven.prembbtraining.model.request.GetProfileRequest;
import com.gaven.prembbtraining.model.response.GetProfileResponse;
import com.gaven.prembbtraining.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProfileService {

    ProfileRepository profileRepository;

    @Autowired
    public GetProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public GetProfileResponse execute(GetProfileRequest request) {
        var profileEntity = profileRepository.findById(request.getUsername());
        return GetProfileResponse.builder()
//                .profile(profileEntity.get())
                .profile(profileEntity.orElseThrow(() -> new NoSuchUsernameException("Profile not found with username: " + request.getUsername())))
                .build();
    }
}
