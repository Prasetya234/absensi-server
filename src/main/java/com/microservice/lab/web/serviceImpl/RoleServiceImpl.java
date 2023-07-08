package com.microservice.lab.web.serviceImpl;

import com.microservice.lab.configuration.exception.NotFoundException;
import com.microservice.lab.web.model.Role;
import com.microservice.lab.web.repository.RoleRepository;
import com.microservice.lab.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAllData() {
        return findAll();
    }

    @Override
    public Role getById(int no) {
        return roleRepository.findById(no).orElseThrow(() -> new NotFoundException("Role Id TIdak tersedia"));
    }

    @Override
    public Role update(int id, Role role) {
        Role updateRole = roleRepository.findById(id).get();
        updateRole.setName(role.getName());
        return roleRepository.save(updateRole);
    }

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findByName(String name) {
        return roleRepository.findByNama(name);
    }

    @Override
    public Map<String, Boolean> delete(int id) {
        roleRepository.deleteById(id);
        Map<String, Boolean> obj = new HashMap<>();
        obj.put("deleted", Boolean.TRUE);
        return obj;
    }

    private List<Role> findAll() {
        return roleRepository.findAll();
    }
}
