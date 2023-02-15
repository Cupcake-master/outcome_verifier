package ru.itis.service;

import ru.itis.model.Role;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface RoleService {

    Role save(Role role);

    Optional<Role> findByName(String name);
}
