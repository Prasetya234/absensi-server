package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.ClassBootcamp;
import com.microservice.lab.web.model.OperationalClass;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.ClassBootcampRepository;
import com.microservice.lab.web.repository.OperationalClassRepository;
import com.microservice.lab.web.repository.RoleRepository;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.ClassBootcampService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ClassBootcampServiceImpl implements ClassBootcampService {

    private ClassBootcampRepository classBootcampRepository;
    private UserRepository userRepository;
    private IAuthenticationFacade authenticationFacade;

    private OperationalClassRepository operationalClassRepository;
    private RoleRepository roleRepository;

    @Autowired
    public ClassBootcampServiceImpl(ClassBootcampRepository classBootcampRepository, UserRepository userRepository, IAuthenticationFacade authenticationFacade, RoleRepository roleRepository, OperationalClassRepository operationalClassRepository) {
        this.classBootcampRepository = classBootcampRepository;
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
        this.operationalClassRepository = operationalClassRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public ClassBootcamp add(ClassBootcamp classBootcamp) {
        OperationalClass operationalClass = operationalClassRepository.save(classBootcamp.getOperationalClass());
        classBootcamp.setOperationalClass(operationalClass);
        return classBootcampRepository.save(classBootcamp);
    }

    @Transactional(readOnly = true)
    @Override
    public ClassBootcamp findById(String id) {
            return classBootcampRepository.findById(id).orElseThrow(() -> new NotFoundException("CLASS ID NOT FOUND"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ClassBootcamp> findAll(Pageable pageable) {
        return classBootcampRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public ClassBootcamp update(String id, ClassBootcamp classBootcamp) {
        ClassBootcamp update = classBootcampRepository.findById(id).orElseThrow(() -> new NotFoundException("Class Bootcamp Id NOT FOUND"));
        update.setName(classBootcamp.getName());
        update.setAddress(classBootcamp.getAddress());
        update.setFoundation(classBootcamp.getFoundation());
        update.setAvatarUrl(classBootcamp.getAvatarUrl());
        update.setLeadInstructor(classBootcamp.getLeadInstructor());
        update.setNumberPhone(classBootcamp.getNumberPhone());
        update.setBackgroundProfile(classBootcamp.getBackgroundProfile());
        OperationalClass operationalClass = operationalClassRepository.save(classBootcamp.getOperationalClass());
        update.setOperationalClass(operationalClass);
        return classBootcampRepository.save(update);
    }

    @Override
    public List<User> findAllStudents() {
        return userRepository.findAllByClassBootcampIdAndRoleId(authenticationFacade.getAuthentication().getClassBootcampId(), roleRepository.findById(1).get());
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
