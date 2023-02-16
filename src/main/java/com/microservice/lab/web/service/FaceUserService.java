package com.microservice.lab.web.service;


import com.microservice.lab.web.dto.FaceDTO;
import com.microservice.lab.web.model.FaceUser;

public interface FaceUserService {
    FaceUser add(FaceDTO faceDTO);
    FaceUser findByUserId(String userId);
    FaceUser edit(String id, FaceDTO faceDTO);
}
