package com.pharmacy.service;

import com.pharmacy.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomEmployeeDetails implements UserDetails {

    private String username;
    private String password;
    private Boolean isLocked;
    private Boolean isActive;
    Collection<? extends GrantedAuthority> authorities;

    public CustomEmployeeDetails(Employee employee) {
        this.username = employee.getUserName();
        this.password = employee.getPassword();
        this.isLocked = employee.getAccountLocked();
        this.isActive = employee.getActive();
        List<GrantedAuthority> auths = new ArrayList<>();

        employee.getRoles().forEach((role) -> {
            role.getPermissions().forEach(permission -> {
                auths.add(new SimpleGrantedAuthority(role.getName().toUpperCase() + "_" + permission.getName()));
            });
        });
//        System.out.println(auths);
        this.authorities = auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}