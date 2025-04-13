package com.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Permission {//Privilege
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 20, unique = true)
    private String name;
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}
