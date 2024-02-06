package com.gaven.prembbtraining.controller;

import com.gaven.prembbtraining.model.request.DeleteProfileRequest;
import com.gaven.prembbtraining.model.request.GetProfileRequest;
import com.gaven.prembbtraining.model.request.PostProfileRequest;
import com.gaven.prembbtraining.model.request.PutProfileRequest;
import com.gaven.prembbtraining.model.response.GetProfileResponse;
import com.gaven.prembbtraining.model.response.GetProfilesResponse;
import com.gaven.prembbtraining.service.*;
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
    private final PutProfileService putProfileService;
    private final DeleteProfileService deleteProfileService;

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

    @CrossOrigin
    @PutMapping("/v1/profile")
    @ResponseStatus(HttpStatus.CREATED)
    public void putProfile(@RequestBody @Valid PutProfileRequest request) {
        putProfileService.execute(request);
    }


    @CrossOrigin
    @DeleteMapping("/v1/profile/{username}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProfile(@PathVariable @Parameter(name = "username", description = "Username", example = "testusername") String username) {
        var request = DeleteProfileRequest.builder()
                .username(username).build();
        deleteProfileService.execute(request);
    }
}
