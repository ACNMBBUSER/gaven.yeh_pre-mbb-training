package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.request.PostProfileRequest;
import com.gaven.prembbtraining.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostProfileService {
    private final ProfileRepository profileRepository;

    public void execute(PostProfileRequest request) {
        profileRepository.save(request.getProfile());
    }
}
