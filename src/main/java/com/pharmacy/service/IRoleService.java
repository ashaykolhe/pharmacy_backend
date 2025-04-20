package com.pharmacy.service;

import com.pharmacy.model.Role;

import java.util.List;

public interface IRoleService {
    Role saveRole(Role role);
    List<Role> saveRoles(List<Role> roles);
}
