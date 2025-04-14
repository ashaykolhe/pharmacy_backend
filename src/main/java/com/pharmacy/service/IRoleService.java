package com.pharmacy.service;

import com.pharmacy.model.Role;

import java.util.List;

public interface IRoleService {
    void saveRole(Role role);
    void saveRoles(List<Role> roles);
}
