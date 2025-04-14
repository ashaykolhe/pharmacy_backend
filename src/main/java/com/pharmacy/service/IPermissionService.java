package com.pharmacy.service;

import com.pharmacy.model.Permission;

import java.util.List;

public interface IPermissionService {
    void savePermission(Permission permission);

    void savePermissions(List<Permission> permissions);
}
