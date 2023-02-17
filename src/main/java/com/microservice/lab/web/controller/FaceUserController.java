package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.FaceDTO;
import com.microservice.lab.web.model.FaceUser;
import com.microservice.lab.web.service.FaceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/face-user")
public class FaceUserController {

    private FaceUserService faceUserService;

    @Autowired
    public FaceUserController(FaceUserService faceUserService) {
        this.faceUserService = faceUserService;
    }

    @PostMapping
    public CommonResponse<FaceUser> add(@RequestBody FaceDTO faceDTO) {
        return ResponseHelper.ok(faceUserService.add(faceDTO));
    }

    @PutMapping("/{id}")
    public CommonResponse<FaceUser> add(@PathVariable("id") String id, @RequestBody FaceDTO faceDTO) {
        return ResponseHelper.ok(faceUserService.edit(id, faceDTO));
    }

    @GetMapping("/{userId}")
    public CommonResponse<FaceUser> findByUser(@PathVariable("userId") String userId)  {
        return ResponseHelper.ok(faceUserService.findByUserId(userId));
    }
}
