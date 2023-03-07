package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public CommonResponse<User> getUserById(@PathVariable("id") String id) {
        return ResponseHelper.ok(userService.getUserById(id));
    }

    @GetMapping
    public CommonResponse<User> getUserByToken() {
        return ResponseHelper.ok(userService.getUserByToken());
    }

    @PutMapping("/{id}")
    public CommonResponse<User> updateUser(@PathVariable("id") String id, @RequestBody User user) {
        return ResponseHelper.ok(userService.updateUser(id, user));
    }
}
