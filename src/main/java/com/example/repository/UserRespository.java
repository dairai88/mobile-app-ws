package com.example.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.ui.model.response.UserRest;

public interface UserRespository extends CrudRepository<UserRest, String> {
    
    Optional<UserRest> findByUserId(String userId);

    void deleteByUserId(String userId);
}
