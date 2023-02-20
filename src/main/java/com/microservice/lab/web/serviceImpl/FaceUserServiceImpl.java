package com.microservice.lab.web.serviceImpl;

import com.google.common.base.Joiner;
import com.microservice.lab.configuration.data.AuthenticationFacade;
import com.microservice.lab.configuration.exception.BussinesException;
import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.dto.FaceDTO;
import com.microservice.lab.web.model.FaceUser;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.FaceUserRepository;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.FaceUserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@Slf4j
@Service
public class FaceUserServiceImpl implements FaceUserService {

    private FaceUserRepository faceUserRepository;
    private UserRepository userRepository;
    private ModelMapper modelMapper;
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public FaceUserServiceImpl(FaceUserRepository faceUserRepository, UserRepository userRepository, ModelMapper modelMapper, AuthenticationFacade authenticationFacade) {
        this.faceUserRepository = faceUserRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.authenticationFacade = authenticationFacade;
    }

    @Transactional
    @Override
    public FaceUser add(FaceDTO faceDTO) {
        User user=  userRepository.findById(faceDTO.getUser()).orElseThrow(() -> new NotFoundException("USER ID NOT FOUND"));
        if(faceUserRepository.findByUserId(user).isPresent()) throw new BussinesException("DATA FACE ALREADY EXIST");
        FaceUser data = modelMapper.map(faceDTO, FaceUser.class);
        data.setDetectorScore(String.join(",", data.getDetectorScores()));
        data.setUserId(user);
        return faceUserRepository.save(data);
    }


    @Override
    public FaceUser findByUserId(String userId) {
        return response(faceUserRepository.findByUserId(userRepository.findById(userId).orElse(null)).orElseThrow(() -> new NotFoundException("FACE NOT FOUND")));
    }

    private FaceUser response(FaceUser faceUser) {
        faceUser.setDetectorScores(Arrays.asList(faceUser.getDetectorScore().split(",")));
        return faceUser;
    }

    @Transactional
    @Override
    public FaceUser edit(String id, FaceDTO faceDTO) {
        FaceUser update = faceUserRepository.findById(id).orElseThrow(() -> new NotFoundException("FACE NOT FOUND"));
        update.setAvatarUrl(update.getAvatarUrl());
        update.setUserId(userRepository.findById(faceDTO.getUser()).orElseThrow(() -> new NotFoundException("USER ID NOT FOUND")));
        update.setDetectorScore(String.join(",", faceDTO.getDetectorScores()));
        return response(faceUserRepository.save(update));
    }
}
