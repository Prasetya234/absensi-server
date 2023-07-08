package com.microservice.lab.web.controller;

import com.microservice.lab.configuration.response.CommonResponse;
import com.microservice.lab.configuration.response.ResponseHelper;
import com.microservice.lab.web.model.Role;
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
        return ResponseHelper.ok(roleService.findAllData());
    }

    @GetMapping("/{id}")
    public CommonResponse<Role> findById(@PathVariable("id") int id) {
        return ResponseHelper.ok(roleService.getById(id));
    }

    @GetMapping("/param")
    public Integer param(@RequestParam(name = "id", required = false) Integer id) {
        return id;
    }

    @GetMapping("/header")
    public CommonResponse<?> header(@RequestHeader("name") String name) {
        return ResponseHelper.ok(name);
    }

    @PostMapping
    public CommonResponse<Role> addRole(@RequestBody Role role) {
        return ResponseHelper.ok(roleService.add(role));
    }

    @PutMapping("/{id}")
    public CommonResponse<Role> updateRole(@PathVariable("id") int id, @RequestBody Role role) {
        return ResponseHelper.ok(roleService.update(id, role));
    }

    @DeleteMapping("/{id}")
    public CommonResponse<Map<String, Boolean>> delete(@PathVariable("id") int id){
        return ResponseHelper.ok(roleService.delete(id));
    }

}
