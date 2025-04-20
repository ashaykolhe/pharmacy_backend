package com.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Permission {//Privilege
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @ToString.Exclude
    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;
}
