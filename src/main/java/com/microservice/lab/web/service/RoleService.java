package com.microservice.lab.web.service;

import com.microservice.lab.web.model.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {
    List<Role> findAllData();
    Role getById(int no);
    Role update(int id, Role role);
    Role add(Role role);
    Map<String, Boolean> delete(int id);
}
