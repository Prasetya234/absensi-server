package com.microservice.lab.configuration.data;

import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade implements IAuthenticationFacade  {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getAuthentication() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).orElse(null);
    }
}
