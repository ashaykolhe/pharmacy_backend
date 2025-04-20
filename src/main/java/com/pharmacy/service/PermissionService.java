package com.pharmacy.service;

import com.pharmacy.model.Permission;
import com.pharmacy.repository.PermissionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class PermissionService implements IPermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public Permission savePermission(Permission permission) {
        log.debug("permission saved " + permission.getName());
        return permissionRepository.save(permission);
    }

    @Override
    public List<Permission> savePermissions(List<Permission> permissions) {
        return permissionRepository.saveAll(permissions);
    }
}
