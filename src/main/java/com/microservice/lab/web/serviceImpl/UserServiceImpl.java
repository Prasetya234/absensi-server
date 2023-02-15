package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private IAuthenticationFacade iAuthenticationFacade;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, IAuthenticationFacade iAuthenticationFacade) {
        this.userRepository = userRepository;
        this.iAuthenticationFacade = iAuthenticationFacade;
    }


    @Transactional
    @Override
    public User getUserById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User id NOT FOUND"));
        User user1 = iAuthenticationFacade.getAuthentication();
        if (user1.getId() != id) {
            user.setViewers(user.getViewers() + 1);
        }
        return userRepository.save(user);
    }
}
