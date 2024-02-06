package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.request.DeleteProfileRequest;
import com.gaven.prembbtraining.repository.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DeleteProfileServiceTest {

    @Mock
    ProfileRepository mockProfileRepository;

    @Captor
    ArgumentCaptor<String> profileRepositoryCaptor;

    @InjectMocks
    DeleteProfileService deleteProfileService;

    @Test
    public void whenReceiveDeleteRequest_thenDeleteFromDB() {
        // Mock request
        var mockRequest = DeleteProfileRequest.builder()
                .username("testUsername")
                .build();


        deleteProfileService.execute(mockRequest);
        Mockito.verify(mockProfileRepository, Mockito.times(1)).deleteById(profileRepositoryCaptor.capture());
        var profileRepositoryInput = profileRepositoryCaptor.getValue();
        Assertions.assertEquals(mockRequest.getUsername(), profileRepositoryInput);
    }

}
