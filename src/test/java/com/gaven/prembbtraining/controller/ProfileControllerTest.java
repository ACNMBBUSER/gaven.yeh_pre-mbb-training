package com.gaven.prembbtraining.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gaven.prembbtraining.model.entity.ProfileEntity;
import com.gaven.prembbtraining.model.exception.NoSuchUsernameException;
import com.gaven.prembbtraining.model.request.GetProfileRequest;
import com.gaven.prembbtraining.model.request.PostProfileRequest;
import com.gaven.prembbtraining.model.response.GetProfileResponse;
import com.gaven.prembbtraining.model.response.GetProfilesResponse;
import com.gaven.prembbtraining.service.GetProfileService;
import com.gaven.prembbtraining.service.GetProfilesService;
import com.gaven.prembbtraining.service.PostProfileService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProfileController.class)
public class ProfileControllerTest {

    @MockBean
    GetProfileService mockGetProfileService;
    @MockBean
    GetProfilesService mockGetProfilesService;
    @MockBean
    PostProfileService mockPostProfileService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getProfilesAPI() throws Exception {
        var mockServiceResponse = GetProfilesResponse.builder()
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

        Mockito.when(mockGetProfilesService.execute()).thenReturn(mockServiceResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/profile")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.profiles").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.profiles[*].username").isNotEmpty());
    }

    @Test
    public void getProfileAPI() throws Exception {
        var mockServiceResponse = GetProfileResponse.builder()
                .profile(
                        ProfileEntity.builder()
                                .username("testUsername1")
                                .age(1)
                                .email("test1@test.com")
                                .name("testName1")
                                .build()
                )
                .build();

        Mockito.when(mockGetProfileService.execute(any(GetProfileRequest.class))).thenReturn(mockServiceResponse);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/profile/" + "testUsername")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.profile").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.profile.username").isNotEmpty());
    }

    @Test
    public void getProfileAPI_404() throws Exception {
        Mockito.when(mockGetProfileService.execute(any(GetProfileRequest.class))).thenThrow(NoSuchUsernameException.class);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/v1/profile/" + "testUsername")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("No such Username"));
    }

    @Test
    public void postProfileAPI() throws Exception {
        var mockRequest = PostProfileRequest.builder()
                .profile(
                        ProfileEntity.builder()
                                .username("testUsername")
                                .age(1)
                                .email("test@test.com")
                                .name("testName")
                                .build())
                .build();

        Mockito.doNothing().when(mockPostProfileService).execute(any(PostProfileRequest.class));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/profile")
                        .content(asJsonString(mockRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").doesNotExist());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
