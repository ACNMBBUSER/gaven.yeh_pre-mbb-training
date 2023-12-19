package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import com.gaven.prembbtraining.model.request.PostProfileRequest;
import com.gaven.prembbtraining.repository.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PostProfileServiceTest {

    @Mock
    ProfileRepository mockProfileRepository;

    @Captor
    ArgumentCaptor<ProfileEntity> profileRepositoryCaptor;

    @InjectMocks
    PostProfileService postProfileService;

    @Test
    public void whenReceivePostRequest_thenSavetoDB() {
        // Mock request
        var mockRequest = PostProfileRequest.builder()
                .profile(ProfileEntity.builder()
                        .username("testUsername")
                        .age(42)
                        .email("test@test.com")
                        .name("testName")
                        .build())
                .build();

        postProfileService.execute(mockRequest);
        Mockito.verify(mockProfileRepository, Mockito.times(1)).save(profileRepositoryCaptor.capture());
        var profileRepositoryInput = profileRepositoryCaptor.getValue();
        Assertions.assertEquals(profileRepositoryInput, mockRequest.getProfile());
    }
}
