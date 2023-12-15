package com.gaven.prembbtraining.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
    private String username;
    private String name;
    private Integer age;
    private String email;
}
