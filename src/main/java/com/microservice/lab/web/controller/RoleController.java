package com.microservice.lab.web.controller;

import com.microservice.lab.web.model.Role;
import com.microservice.lab.web.response.CommonResponse;
import com.microservice.lab.web.response.CommonResponseGenerator;
import com.microservice.lab.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService  roleService;

    @GetMapping
    public CommonResponse<List<Role>> findAllData() {
        return CommonResponseGenerator.responseSukses(roleService.findAllData());
    }

    @GetMapping("/{id}")
    public CommonResponse<Role> findById(@PathVariable("id") int id) {
        return CommonResponseGenerator.responseSukses(roleService.getById(id));
    }

    @GetMapping("/param")
    public Integer param(@RequestParam(name = "id", required = false) Integer id) {
        return id;
    }

    @GetMapping("/header")
    public CommonResponse<?> header(@RequestHeader("name") String name) {
        return CommonResponseGenerator.responseSukses(name);
    }

    @PostMapping
    public CommonResponse<Role> addRole(@RequestBody Role role) {
        return CommonResponseGenerator.responseSukses(roleService.add(role));
    }

    @PutMapping("/{id}")
    public CommonResponse<Role> updateRole(@PathVariable("id") int id, @RequestBody Role role) {
        return CommonResponseGenerator.responseSukses(roleService.update(id, role));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Map<String, Boolean>> delete(@PathVariable("id") int id){
        return CommonResponseGenerator.responseSukses(roleService.delete(id));
    }

    @GetMapping("/serach/{name}")
    public CommonResponse<List<Role>> findByName(@PathVariable("name") String name) {
        return CommonResponseGenerator.responseSukses(roleService.findByName(name));
    }
}
