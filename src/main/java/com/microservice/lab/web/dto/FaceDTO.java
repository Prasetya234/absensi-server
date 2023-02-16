package com.microservice.lab.web.dto;

import com.microservice.lab.web.model.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class FaceDTO {
    private String avatarUrl;
    private String user;
    private List<String> detectorScores;
}
