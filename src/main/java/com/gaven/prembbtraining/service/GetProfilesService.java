package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.response.GetProfilesResponse;
import com.gaven.prembbtraining.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProfilesService {
    ProfileRepository profileRepository;

    @Autowired

    public GetProfilesService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public GetProfilesResponse execute() {
        var profileEntities = profileRepository.findAll();
        return GetProfilesResponse.builder()
                .profiles(profileEntities)
                .build();
    }
}
