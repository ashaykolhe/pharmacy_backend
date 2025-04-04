package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double salary;
    private LocalDate joiningDate;
    private LocalDate resignationDate;
    @OneToOne(mappedBy = "payroll")
    private Employee employee;
}
