package com.example.userrole.service;

import com.example.userrole.entity.Role;
import com.example.userrole.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepo roleRepository;

    public Role createRole(Role role) {
        // You can add validation logic here
        return roleRepository.save(role);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }
}
