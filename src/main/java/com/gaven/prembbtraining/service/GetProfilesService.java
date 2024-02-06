package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.response.GetProfilesResponse;
import com.gaven.prembbtraining.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class GetProfilesService {
    private final ProfileRepository profileRepository;


    public GetProfilesResponse execute() {
        var profileEntities = profileRepository.findAll();
        return GetProfilesResponse.builder()
                .profiles(profileEntities)
                .build();
    }
}
