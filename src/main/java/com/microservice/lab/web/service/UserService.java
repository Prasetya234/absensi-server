package com.microservice.lab.web.service;

import com.microservice.lab.web.model.TokenTemporary;
import com.microservice.lab.web.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {

    User getUserById(String id);
}
