package ru.itis.service;

import ru.itis.model.Role;
import ru.itis.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public Optional<Role> findByName(String name) {
        return roleRepository.findByName(name);
    }
}
