package com.gaven.prembbtraining.model.response;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class GetProfileResponse {
    private ProfileEntity profile;
}
