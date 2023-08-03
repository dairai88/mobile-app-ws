package com.example.userservice;

import com.example.ui.model.request.UserDetailsRequestModel;
import com.example.ui.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetails);
}
