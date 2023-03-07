package com.microservice.lab.web.service;

import com.microservice.lab.web.model.User;

public interface UserService {

    User getUserById(String id);
    User getUserByToken();
    User updateUser(String id, User user);
}
