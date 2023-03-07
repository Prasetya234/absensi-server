package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.dto.RegisterRequest;
import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.SchoolRepository;
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

    @Override
    public User getUserByToken() {
        return iAuthenticationFacade.getAuthentication();
    }

    @Transactional
    @Override
    public User updateUser(String id, User user) {
        User user1 = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User Id NOT FOUND"));
        user1.setAvatarUrl(user.getAvatarUrl());
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setBirthDate(user.getBirthDate());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());
        user1.setNoSiswa(user.getNoSiswa());
        user1.setBatch(user.getBatch());
        user1.setFavorite(user.getFavorite());
        user1.setSchoolClass(user.getSchoolClass());
        user1.setDescription(user.getDescription());
        return userRepository.save(user1);
    }
}
