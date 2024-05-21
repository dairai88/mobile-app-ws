package com.example.ui.controller;

import com.example.exceptions.UserServiceException;
import com.example.ui.model.request.UpdateUserDetailsRequestModel;
import com.example.ui.model.request.UserDetailsRequestModel;
import com.example.ui.model.response.UserRest;
import com.example.userservice.UserService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public Iterable<UserRest> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "/{userId}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
        if (userId.equals("test")) {
            throw new UserServiceException("A user service exception is thrown");
        }

        UserRest user = userService.getUser(userId);

        if (user == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(user);
        }

    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

        return ResponseEntity.ok(userService.createUser(userDetails));
    }

    @PutMapping(path = "/{userId}", consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    })
    public UserRest updateUser(@PathVariable String userId,
                             @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
                               
        return userService.updateUser(userId, userDetails);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) {

        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
}