package com.gaven.prembbtraining.controller;

import com.gaven.prembbtraining.model.request.GetProfileRequest;
import com.gaven.prembbtraining.model.request.PostProfileRequest;
import com.gaven.prembbtraining.model.response.GetProfileResponse;
import com.gaven.prembbtraining.model.response.GetProfilesResponse;
import com.gaven.prembbtraining.service.GetProfileService;
import com.gaven.prembbtraining.service.GetProfilesService;
import com.gaven.prembbtraining.service.PostProfileService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final GetProfileService getProfileService;
    private final GetProfilesService getProfilesService;
    private final PostProfileService postProfileService;

    @CrossOrigin
    @GetMapping("/v1/profile")
    public GetProfilesResponse getProfiles() {
        return getProfilesService.execute();
    }

    @CrossOrigin
    @GetMapping("/v1/profile/{username}")
    public GetProfileResponse getProfile(@PathVariable @Parameter(name = "username", description = "Username", example = "testusername") String username) {
        var request = GetProfileRequest.builder()
                .username(username).build();
        return getProfileService.execute(request);
    }

    @CrossOrigin
    @PostMapping("/v1/profile")
    @ResponseStatus(HttpStatus.CREATED)
    public void postProfile(@RequestBody @Valid PostProfileRequest request) {
        postProfileService.execute(request);
    }
}
