package com.microservice.lab.web.service;

import com.microservice.lab.web.model.Reason;

import java.util.List;
import java.util.Map;

public interface ReasonService {
    List<Reason> findAll();

    Reason findById(Integer id);

    Reason add(Reason reason);

    Reason update(Integer id, Reason reason);

    Map<String, Object> delete(Integer id);
}
