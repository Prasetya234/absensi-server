package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.model.GoingHomeEarly;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.service.GoingHomeEarlyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/going-home-early")
public class GoingHomeEarlyController {

    private GoingHomeEarlyService goingHomeEarlyService;

    private IAuthenticationFacade iAuthenticationFacade;

    @Autowired
    public GoingHomeEarlyController(GoingHomeEarlyService goingHomeEarlyService, IAuthenticationFacade iAuthenticationFacade) {
        this.goingHomeEarlyService = goingHomeEarlyService;
        this.iAuthenticationFacade = iAuthenticationFacade;
    }

    @GetMapping
    public CommonResponse<List<GoingHomeEarly>> findAll() {
        return ResponseHelper.ok(goingHomeEarlyService.findAll());
    }

    @PostMapping
    public CommonResponse<GoingHomeEarly> add(@RequestBody GoingHomeEarly goingHomeEarly) {
        User user = iAuthenticationFacade.getAuthentication();
        goingHomeEarly.setClassBootcampId(user.getClassBootcampId());
        return ResponseHelper.ok(goingHomeEarlyService.add(goingHomeEarly));
    }
//
//    @PutMapping("/{id}")
//    public CommonResponse<GoingHomeEarly> update(@PathVariable("id") Integer id, @RequestBody GoingHomeEarly goingHomeEarly) {
//        return ResponseHelper.ok(goingHomeEarlyService.update(id, goingHomeEarly));
//    }

    @DeleteMapping("/{id}")
    public CommonResponse<Object> delete(@PathVariable("id") Integer id) {
        return ResponseHelper.ok(goingHomeEarlyService.delete(id));
    }
}
