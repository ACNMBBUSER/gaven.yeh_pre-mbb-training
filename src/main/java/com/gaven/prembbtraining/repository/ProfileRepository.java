package com.gaven.prembbtraining.repository;

import com.gaven.prembbtraining.model.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, String> {

}
