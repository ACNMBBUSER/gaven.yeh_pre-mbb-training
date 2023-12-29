package com.gaven.prembbtraining.model.response;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class GetProfilesResponse {
    @ArraySchema(schema = @Schema(implementation = ProfileEntity.class))
    private Iterable<ProfileEntity> profiles;
}
