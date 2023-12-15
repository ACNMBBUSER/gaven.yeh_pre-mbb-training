package com.gaven.prembbtraining.repository;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {

    @Override
    Optional<ProfileEntity> findById(String s);

    @Override
    Iterable<ProfileEntity> findAll();
}
