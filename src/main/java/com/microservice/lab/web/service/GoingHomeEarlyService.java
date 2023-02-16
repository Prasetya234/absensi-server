package com.microservice.lab.web.service;

import com.microservice.lab.web.model.GoingHomeEarly;

import java.util.List;
import java.util.Map;

public interface GoingHomeEarlyService {

    List<GoingHomeEarly> findAll();

    GoingHomeEarly add(GoingHomeEarly goingHomeEarly);

//    GoingHomeEarly update(Integer id, GoingHomeEarly goingHomeEarly);

    Map<String, Object> delete(Integer id);
}
