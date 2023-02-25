package com.microservice.lab.web.service;

import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface SchoolService {
    School add(School school);
    School findById(String id);
    Page<School> findAll(Pageable pageable);
    School update(String id, School school);
    List<User> findAllStudents();
    Map<String, Object> delete(String id);
}
