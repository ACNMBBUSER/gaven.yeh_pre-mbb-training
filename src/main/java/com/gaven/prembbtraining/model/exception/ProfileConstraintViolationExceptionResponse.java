package com.gaven.prembbtraining.model.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
public class ProfileConstraintViolationExceptionResponse {

    @Schema(example = "Bad Request")
    private String description;

    @JsonProperty("problem_fields")
    private List<ProblemFields> problemFields;

    @Data
    @RequiredArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ProblemFields {
        @Schema(example = "email", description = "field with error")
        private String field;
        @Schema(example = "invalid email address")
        private String reason;
    }
}
