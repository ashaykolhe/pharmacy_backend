package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Column(nullable = false)
    private Integer loyaltyPoints;
    @Column(nullable = false)
    private LocalDateTime loyaltyStartDate;
    @Column(nullable = false)
    private LocalDateTime loyaltyEndDate;//validTill
    @Column(nullable = false)
    private Boolean active;
}
