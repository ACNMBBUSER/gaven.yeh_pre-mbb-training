package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import com.gaven.prembbtraining.model.exception.NoSuchUsernameException;
import com.gaven.prembbtraining.model.request.PutProfileRequest;
import com.gaven.prembbtraining.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Service
@RequiredArgsConstructor
@Slf4j
public class PutProfileService {
    private final ProfileRepository profileRepository;

    public void execute(PutProfileRequest request) {

        var profile = profileRepository.findById(request.getProfile().getUsername())
                .orElseThrow(() -> new NoSuchUsernameException("Profile not found with username: " + request.getProfile().getUsername()));
        var updatedProfile = ProfileEntity.builder()
                .username(profile.getUsername())
                .name(defaultIfNull(request.getProfile().getName(), profile.getName()))
                .age(defaultIfNull(request.getProfile().getAge(), profile.getAge()))
                .email(defaultIfNull(request.getProfile().getEmail(), profile.getEmail()))
                .build();

        profileRepository.save(updatedProfile);
    }
}
