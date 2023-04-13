package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.data.IAuthenticationFacade;
import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.dto.SchoolDTO;
import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.repository.RoleRepository;
import com.microservice.lab.web.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
    private SchoolService schoolService;

    private IAuthenticationFacade authenticationFacade;
    private RoleRepository roleRepository;

    @Autowired
    public SchoolController(SchoolService schoolService, IAuthenticationFacade authenticationFacade, RoleRepository roleRepository) {
        this.schoolService = schoolService;
        this.authenticationFacade = authenticationFacade;
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping
    public CommonResponse<School> add(@RequestBody SchoolDTO schoolDTO) {
        return ResponseHelper.ok(schoolService.add(schoolDTO));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/students")
    public CommonResponse<List<User>> findAllStudents(@RequestParam(name = "keyword", required = false) String keyword) {
        return ResponseHelper.ok(schoolService.findAllStudents(keyword));
    }

    @GetMapping("/{id}")
    public CommonResponse<School> findById(@PathVariable("id") String id) {
        return ResponseHelper.ok(schoolService.findById(id));
    }

    @GetMapping
    public CommonResponse<Page<School>> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseHelper.ok(schoolService.findAll(pageable));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{id}")
    public CommonResponse<School> update(@PathVariable("id") String id, @RequestBody School school) {
        return ResponseHelper.ok(schoolService.update(id, school));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public CommonResponse<Object> delete(@PathVariable("id") String id) {
        return ResponseHelper.ok(schoolService.delete(id));
    }
}
