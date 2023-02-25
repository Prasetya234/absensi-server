package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.model.School;
import com.microservice.lab.web.model.User;
import com.microservice.lab.web.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/class-bootcamp")
public class SchoolController {
    private SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping
    public CommonResponse<School> add(@RequestBody School school) {
        return ResponseHelper.ok(schoolService.add(school));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/students")
    public CommonResponse<List<User>> findAllStudents() {
        return ResponseHelper.ok(schoolService.findAllStudents());
    }

    @GetMapping("/{id}")
    public CommonResponse<School> findById(@PathVariable("id") String id) {
        return ResponseHelper.ok(schoolService.findById(id));
    }

    @GetMapping
    public CommonResponse<Page<School>> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
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
