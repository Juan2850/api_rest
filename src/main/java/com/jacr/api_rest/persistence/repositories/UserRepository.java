package com.jacr.api_rest.persistence.repositories;

import com.jacr.api_rest.persistence.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findUserEntityByUsername(String username);
}
