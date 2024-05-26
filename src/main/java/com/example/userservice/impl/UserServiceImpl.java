package com.example.userservice.impl;

import com.example.exceptions.UserServiceException;
import com.example.repository.UserRespository;
import com.example.shared.Utils;
import com.example.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.ui.model.request.UserDetailsRequestModel;
import com.example.ui.model.response.UserRest;
import com.example.userservice.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRespository userRespository;
    private final Utils utils;

    public UserServiceImpl(UserRespository userRespository, Utils utils) {
        this.userRespository = userRespository;
        this.utils = utils;
    }

    @Override
    public Iterable<UserRest> getUsers() {
        return userRespository.findAll();
    }

    @Override
    public UserRest getUser(String userId) {
        return userRespository.findByUserId(userId)
                .orElseThrow(() -> new UserServiceException("User not found"));
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = utils.generateUserId();
        returnValue.setUserId(userId);

        userRespository.save(returnValue);

        return returnValue;
    }

    @Override
    public UserRest updateUser(String userId, UpdateUserDetailsRequestModel userDetails) {

        UserRest storedUserDetails = userRespository.findByUserId(userId)
                .orElseThrow(() -> new UserServiceException("User not found"));
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        userRespository.save(storedUserDetails);

        return storedUserDetails;
    }

    @Override
    public void deleteUser(String userId) {
        userRespository.deleteByUserId(userId);
    }
}
