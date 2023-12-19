package com.gaven.prembbtraining.service;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import com.gaven.prembbtraining.model.response.GetProfilesResponse;
import com.gaven.prembbtraining.repository.ProfileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class GetProfilesServiceTest {

    @Mock
    ProfileRepository mockProfileRepository;

    @InjectMocks
    GetProfilesService getProfilesService;

    @Test
    public void whenReceivePostRequest_thenSavetoDB() {
        // Expected Response
        var expectedResponse = GetProfilesResponse.builder()
                .profiles(List.of(
                        ProfileEntity.builder()
                                .username("testUsername1")
                                .age(1)
                                .email("test1@test.com")
                                .name("testName1")
                                .build(),
                        ProfileEntity.builder()
                                .username("testUsername2")
                                .age(2)
                                .email("test2@test.com")
                                .name("testName2")
                                .build()
                ))
                .build();

        Mockito.when(mockProfileRepository.findAll()).thenReturn(List.of(
                ProfileEntity.builder()
                        .username("testUsername1")
                        .age(1)
                        .email("test1@test.com")
                        .name("testName1")
                        .build(),
                ProfileEntity.builder()
                        .username("testUsername2")
                        .age(2)
                        .email("test2@test.com")
                        .name("testName2")
                        .build()
        ));

        Assertions.assertEquals(expectedResponse, getProfilesService.execute());
        Mockito.verify(mockProfileRepository, Mockito.times(1)).findAll();
    }
}
