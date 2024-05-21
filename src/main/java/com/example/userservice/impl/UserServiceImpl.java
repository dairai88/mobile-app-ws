package com.example.userservice.impl;

import com.example.shared.Utils;
import com.example.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.ui.model.request.UserDetailsRequestModel;
import com.example.ui.model.response.UserRest;
import com.example.userservice.UserService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    Map<String, UserRest> users;
    Utils utils;

    public UserServiceImpl(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest getUser(String userId) {
        if (users == null) {
            return null;
        }

        return users.get(userId);
    }

    @Override
    public Iterable<UserRest> getUsers() {
        if (users == null) {
            return new ArrayList<>();
        }

        return users.values();
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {
        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = utils.generateUserId();
        returnValue.setUserId(userId);

        if (users == null) {
            users = new HashMap<>();
        }
        users.put(userId, returnValue);

        return returnValue;
    }

    @Override
    public UserRest updateUser(String userId, UpdateUserDetailsRequestModel userDetails) {
        
        UserRest storedUserDetails = users.get(userId);
        storedUserDetails.setFirstName(userDetails.getFirstName());
        storedUserDetails.setLastName(userDetails.getLastName());

        users.put(userId, storedUserDetails);

        return storedUserDetails;
    }

    @Override
    public void deleteUser(String userId) {
        users.remove(userId);
    }
    
}
