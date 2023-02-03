package com.microservice.lab.web.service;

import com.microservice.lab.web.model.ClassBootcamp;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ClassBootcampService {
    ClassBootcamp add(ClassBootcamp classBootcamp);
    Page<ClassBootcamp> findAll(Pageable pageable);
    ClassBootcamp update(String id, ClassBootcamp classBootcamp);
    Map<String, Object> delete(String id);
}
