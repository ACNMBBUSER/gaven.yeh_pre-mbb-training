package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.request.PostProfileRequest;
import com.gaven.prembbtraining.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostProfileService {
    ProfileRepository profileRepository;

    @Autowired

    public PostProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void execute(PostProfileRequest request) {
        profileRepository.save(request.getProfile());
    }
}
