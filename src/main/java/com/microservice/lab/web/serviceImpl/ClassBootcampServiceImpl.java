package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.ClassBootcamp;
import com.microservice.lab.web.repository.ClassBootcampRepository;
import com.microservice.lab.web.service.ClassBootcampService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class ClassBootcampServiceImpl implements ClassBootcampService {

    private ClassBootcampRepository classBootcampRepository;

    @Autowired
    public ClassBootcampServiceImpl(ClassBootcampRepository classBootcampRepository) {
        this.classBootcampRepository = classBootcampRepository;
    }

    @Transactional
    @Override
    public ClassBootcamp add(ClassBootcamp classBootcamp) {
        return classBootcampRepository.save(classBootcamp);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ClassBootcamp> findAll(Pageable pageable) {
        return classBootcampRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public ClassBootcamp update(String id, ClassBootcamp classBootcamp) {
        ClassBootcamp create = classBootcampRepository.findById(id).orElseThrow(() -> new NotFoundException("Class Bootcamp Id NOT FOUND"));
        create.setName(classBootcamp.getName());
        create.setAddress(classBootcamp.getAddress());
        create.setFoundation(classBootcamp.getFoundation());
        create.setAvatarUrl(classBootcamp.getAvatarUrl());
        create.setLeadInstructor(classBootcamp.getLeadInstructor());
        create.setNumberPhone(classBootcamp.getNumberPhone());
        create.setTotalStudent(0);
        return classBootcampRepository.save(create);
    }

    @Transactional
    @Override
    public Map<String, Object> delete(String id) {
        try {
            classBootcampRepository.deleteById(id);
            Map<String, Object> obj = new HashMap<>();
            obj.put("deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            throw new NotFoundException("Class Bootcamp ID NOT FOUND");
        }

    }
}
