package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import com.gaven.prembbtraining.model.exception.NoSuchUsernameException;
import com.gaven.prembbtraining.model.request.GetProfileRequest;
import com.gaven.prembbtraining.model.response.GetProfileResponse;
import com.gaven.prembbtraining.repository.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class GetProfileServiceTest {

    @Mock
    ProfileRepository mockProfileRepository;

    @Captor
    ArgumentCaptor<String> profileRepositoryCaptor;

    @InjectMocks
    GetProfileService getProfileService;

    @Test
    public void whenReceiveGetRequest_thenReturnProfile() {
        // Mock request
        var mockRequest = GetProfileRequest.builder()
                .username("testUsername")
                .build();

        // Expected Response
        var expectedResponse = GetProfileResponse.builder()
                .profile(ProfileEntity.builder()
                        .username("testUsername")
                        .age(42)
                        .email("test@test.com")
                        .name("testName")
                        .build())
                .build();

        Mockito.when(mockProfileRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.ofNullable(ProfileEntity.builder()
                .username("testUsername")
                .age(42)
                .email("test@test.com")
                .name("testName")
                .build()));

        getProfileService.execute(mockRequest);
        Mockito.verify(mockProfileRepository, Mockito.times(1)).findById(profileRepositoryCaptor.capture());
        var profileRepositoryInput = profileRepositoryCaptor.getValue();
        Assertions.assertEquals(mockRequest.getUsername(), profileRepositoryInput);
        Assertions.assertEquals(expectedResponse, getProfileService.execute(mockRequest));
    }

    @Test
    public void whenUsernameNotExist_thenThrowException() {
        // Mock request
        var mockRequest = GetProfileRequest.builder()
                .username("testUsername")
                .build();

        Mockito.when(mockProfileRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchUsernameException.class, () -> getProfileService.execute(mockRequest));
        Mockito.verify(mockProfileRepository, Mockito.times(1)).findById(profileRepositoryCaptor.capture());
        var profileRepositoryInput = profileRepositoryCaptor.getValue();
        Assertions.assertEquals(mockRequest.getUsername(), profileRepositoryInput);
    }
}
