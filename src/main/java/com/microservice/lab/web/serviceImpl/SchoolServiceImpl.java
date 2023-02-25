package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.OperationalClass;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.SchoolRepository;
import com.microservice.lab.web.repository.OperationalClassRepository;
import com.microservice.lab.web.repository.RoleRepository;
import com.microservice.lab.web.repository.UserRepository;
import com.microservice.lab.web.service.SchoolService;
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
public class SchoolServiceImpl implements SchoolService {

    private SchoolRepository schoolRepository;
    private UserRepository userRepository;
    private IAuthenticationFacade authenticationFacade;

    private OperationalClassRepository operationalClassRepository;
    private RoleRepository roleRepository;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository, UserRepository userRepository, IAuthenticationFacade authenticationFacade, RoleRepository roleRepository, OperationalClassRepository operationalClassRepository) {
        this.schoolRepository = schoolRepository;
        this.userRepository = userRepository;
        this.authenticationFacade = authenticationFacade;
        this.operationalClassRepository = operationalClassRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public School add(School school) {
        OperationalClass operationalClass = operationalClassRepository.save(school.getOperationalClass());
        school.setOperationalClass(operationalClass);
        return schoolRepository.save(school);
    }

    @Transactional(readOnly = true)
    @Override
    public School findById(String id) {
            return schoolRepository.findById(id).orElseThrow(() -> new NotFoundException("SCHOOL ID NOT FOUND"));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<School> findAll(Pageable pageable) {
        return schoolRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public School update(String id, School school) {
        School update = schoolRepository.findById(id).orElseThrow(() -> new NotFoundException("School Id NOT FOUND"));
        update.setName(school.getName());
        update.setAddress(school.getAddress());
        update.setFoundation(school.getFoundation());
        update.setAvatarUrl(school.getAvatarUrl());
        update.setLeadInstructor(school.getLeadInstructor());
        update.setNumberPhone(school.getNumberPhone());
        update.setBackgroundProfile(school.getBackgroundProfile());
        OperationalClass operationalClass = operationalClassRepository.save(school.getOperationalClass());
        update.setOperationalClass(operationalClass);
        return schoolRepository.save(update);
    }

    @Override
    public List<User> findAllStudents() {
        return userRepository.findAllBySchoolIdAndRoleId(authenticationFacade.getAuthentication().getSchoolId(), roleRepository.findById(1).get());
    }

    @Transactional
    @Override
    public Map<String, Object> delete(String id) {
        try {
            schoolRepository.deleteById(id);
            Map<String, Object> obj = new HashMap<>();
            obj.put("deleted", Boolean.TRUE);
            return obj;
        } catch (Exception e) {
            throw new NotFoundException("School ID NOT FOUND");
        }

    }
}
