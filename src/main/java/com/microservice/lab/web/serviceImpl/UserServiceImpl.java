package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User id NOT FOUND"));
    }
}
