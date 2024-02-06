package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import com.gaven.prembbtraining.model.exception.NoSuchUsernameException;
import com.gaven.prembbtraining.model.request.PutProfileRequest;
import com.gaven.prembbtraining.repository.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PutProfileServiceTest {

    @Mock
    ProfileRepository mockProfileRepository;

    @Captor
    ArgumentCaptor<ProfileEntity> profileRepositoryCaptor;

    @Captor
    ArgumentCaptor<String> profileRepositoryUsernameCaptor;

    @InjectMocks
    PutProfileService putProfileService;

    @Test
    public void whenReceivePutRequest_thenSaveToDB() {
        // Mock request
        var mockRequest = PutProfileRequest.builder()
                .profile(ProfileEntity.builder()
                        .username("testUsername")
                        .age(42)
                        .email("test@test.com")
                        .name("testName")
                        .build())
                .build();

        Mockito.when(mockProfileRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.ofNullable(ProfileEntity.builder()
                .username("testUsername")
                .age(40)
                .email("test@oldtest.com")
                .name("oldtestName")
                .build()));

        putProfileService.execute(mockRequest);
        Mockito.verify(mockProfileRepository, Mockito.times(1)).save(profileRepositoryCaptor.capture());
        var profileRepositoryInput = profileRepositoryCaptor.getValue();
        Assertions.assertEquals(profileRepositoryInput, mockRequest.getProfile());
    }

    @Test
    public void whenReceivePutRequestWithNullName_thenSaveToDBWithoutUpdatingName() {
        // Mock request
        var mockRequest = PutProfileRequest.builder()
                .profile(ProfileEntity.builder()
                        .username("testUsername")
                        .age(42)
                        .email("test@test.com")
                        .build())
                .build();

        Mockito.when(mockProfileRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.ofNullable(ProfileEntity.builder()
                .username("testUsername")
                .age(40)
                .email("test@oldtest.com")
                .name("oldTestName")
                .build()));

        var expectedSavedEntity = mockRequest.getProfile();
        expectedSavedEntity.setName("oldTestName");

        putProfileService.execute(mockRequest);
        Mockito.verify(mockProfileRepository, Mockito.times(1)).save(profileRepositoryCaptor.capture());
        var profileRepositoryInput = profileRepositoryCaptor.getValue();
        Assertions.assertEquals(profileRepositoryInput, expectedSavedEntity);
    }

    @Test
    public void whenReceivePutRequestWithNullAge_thenSaveToDBWithoutUpdatingAge() {
        // Mock request
        var mockRequest = PutProfileRequest.builder()
                .profile(ProfileEntity.builder()
                        .username("testUsername")
                        .email("test@test.com")
                        .name("testName")
                        .build())
                .build();

        Mockito.when(mockProfileRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.ofNullable(ProfileEntity.builder()
                .username("testUsername")
                .age(40)
                .email("test@oldtest.com")
                .name("oldTestName")
                .build()));

        var expectedSavedEntity = mockRequest.getProfile();
        expectedSavedEntity.setAge(40);

        putProfileService.execute(mockRequest);
        Mockito.verify(mockProfileRepository, Mockito.times(1)).save(profileRepositoryCaptor.capture());
        var profileRepositoryInput = profileRepositoryCaptor.getValue();
        Assertions.assertEquals(profileRepositoryInput, expectedSavedEntity);
    }

    @Test
    public void whenReceivePutRequestWithNullEmail_thenSaveToDBWithoutUpdatingEmail() {
        // Mock request
        var mockRequest = PutProfileRequest.builder()
                .profile(ProfileEntity.builder()
                        .username("testUsername")
                        .age(42)
                        .name("testName")
                        .build())
                .build();

        Mockito.when(mockProfileRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.ofNullable(ProfileEntity.builder()
                .username("testUsername")
                .age(40)
                .email("test@oldtest.com")
                .name("oldTestName")
                .build()));

        var expectedSavedEntity = mockRequest.getProfile();
        expectedSavedEntity.setEmail("test@oldtest.com");

        putProfileService.execute(mockRequest);
        Mockito.verify(mockProfileRepository, Mockito.times(1)).save(profileRepositoryCaptor.capture());
        var profileRepositoryInput = profileRepositoryCaptor.getValue();
        Assertions.assertEquals(profileRepositoryInput, expectedSavedEntity);
    }

    @Test
    public void whenUsernameNotExist_thenThrowException() {
        // Mock request
        var mockRequest = PutProfileRequest.builder()
                .profile(ProfileEntity.builder()
                        .username("testUsername")
                        .age(42)
                        .name("testName")
                        .build())
                .build();

        Mockito.when(mockProfileRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(NoSuchUsernameException.class, () -> putProfileService.execute(mockRequest));
        Mockito.verify(mockProfileRepository, Mockito.times(1)).findById(profileRepositoryUsernameCaptor.capture());
        var profileRepositoryInput = profileRepositoryUsernameCaptor.getValue();
        Assertions.assertEquals(mockRequest.getProfile().getUsername(), profileRepositoryInput);
    }
}
