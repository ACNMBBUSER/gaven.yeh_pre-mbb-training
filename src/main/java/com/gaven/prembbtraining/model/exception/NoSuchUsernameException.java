package com.gaven.prembbtraining.model.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Username")
public class NoSuchUsernameException extends NoSuchElementException {
    public NoSuchUsernameException(String message) {
        super(message);
    }

    public ResponseBody responseBody;

    @Data
    @RequiredArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class ResponseBody {
        @JsonProperty("error message")
        @Schema(example = "Profile not found with username: foobar")
        public String errorMessage;
    }
}
