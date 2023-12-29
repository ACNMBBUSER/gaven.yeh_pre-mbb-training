package com.gaven.prembbtraining.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Entity(name = "Profiles")
public class ProfileEntity {

    @Id
    @NotBlank(message = "username should not be null")
    @Size(max = 20)
    @Schema(example = "foo-bar")
    private String username;

    @Schema(example = "Foo Bar", description = "Full Name")
    private String name;

    @Schema(example = "42")
    private Integer age;

    @Email(message = "invalid email address")
    @Schema(example = "Foo@Bar.com")
    private String email;
}
