package com.pharmacy.service;

import com.pharmacy.model.Permission;

import java.util.List;

public interface IPermissionService {
    Permission savePermission(Permission permission);

    List<Permission> savePermissions(List<Permission> permissions);
}
