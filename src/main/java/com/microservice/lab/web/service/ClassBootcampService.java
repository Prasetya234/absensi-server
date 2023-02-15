package com.microservice.lab.web.service;

import com.microservice.lab.web.model.ClassBootcamp;
import com.microservice.lab.web.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ClassBootcampService {
    ClassBootcamp add(ClassBootcamp classBootcamp);
    ClassBootcamp findById(String id);
    Page<ClassBootcamp> findAll(Pageable pageable);
    ClassBootcamp update(String id, ClassBootcamp classBootcamp);
    List<User> findAllStudents();
    Map<String, Object> delete(String id);
}
