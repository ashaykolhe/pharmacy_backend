package com.pharmacy.service;

import com.pharmacy.model.Role;
import com.pharmacy.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class RoleService implements IRoleService {
    private final RoleRepository roleRepository;

    @Override
    public void saveRole(Role role) {
        log.debug("role saved " + role.getName());
        roleRepository.save(role);
    }

    @Override
    public void saveRoles(List<Role> roles) {
        roles.forEach(this::saveRole);
    }
}
