package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;
    @CreationTimestamp
    private LocalDateTime createdTimestamp;
    @OneToOne
    @JoinColumn(name = "permission_id", referencedColumnName = "id", nullable = false)
    private Permission permission;
}
