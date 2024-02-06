package com.gaven.prembbtraining.model.request;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class PutProfileRequest {
    @NotNull
    private ProfileEntity profile;
}
