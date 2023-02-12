package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.model.ClassBootcamp;
import com.microservice.lab.web.service.ClassBootcampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/class-bootcamp")
public class ClassBootcampController {
    private ClassBootcampService classBootcampService;

    @Autowired
    public ClassBootcampController(ClassBootcampService classBootcampService) {
        this.classBootcampService = classBootcampService;
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping
    public CommonResponse<ClassBootcamp> add(@RequestBody ClassBootcamp classBootcamp) {
        return ResponseHelper.ok(classBootcampService.add(classBootcamp));
    }

    @GetMapping("/{id}")
    public CommonResponse<ClassBootcamp> findById(@PathVariable("id") String id) {
        return ResponseHelper.ok(classBootcampService.findById(id));
    }

    @GetMapping
    public CommonResponse<Page<ClassBootcamp>> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") int page, @RequestParam(name = "size", required = false, defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page,size);
        return ResponseHelper.ok(classBootcampService.findAll(pageable));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{id}")
    public CommonResponse<ClassBootcamp> update(@PathVariable("id") String id, @RequestBody ClassBootcamp classBootcamp) {
        return ResponseHelper.ok(classBootcampService.update(id, classBootcamp));
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public CommonResponse<Object> delete(@PathVariable("id") String id) {
        return ResponseHelper.ok(classBootcampService.delete(id));
    }
}
