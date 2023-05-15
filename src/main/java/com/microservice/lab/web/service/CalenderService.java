package com.microservice.lab.web.service;

import com.microservice.lab.web.dto.CalenderRequest;
import com.microservice.lab.web.model.Calender;

import java.util.Date;
import java.util.List;

public interface CalenderService {
    Calender add(CalenderRequest calenderRequest);
    List<Calender> findAll(Integer month, Integer year);
    Calender findById(int id);
}
