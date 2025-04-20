package com.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 10)
    private Double salary;
    @Column(nullable = false)
    private LocalDate joiningDate;
    private LocalDate resignationDate;
    @JsonIgnore
    @OneToOne(mappedBy = "payroll")
    private Employee employee;
}
