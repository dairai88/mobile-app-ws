package com.example.userservice;

import com.example.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.ui.model.request.UserDetailsRequestModel;
import com.example.ui.model.response.UserRest;

public interface UserService {
    
    Iterable<UserRest> getUsers();

    UserRest getUser(String userId);

    UserRest createUser(UserDetailsRequestModel userDetails);

    UserRest updateUser(String userId, UpdateUserDetailsRequestModel userDetails);

    void deleteUser(String userId);
}
