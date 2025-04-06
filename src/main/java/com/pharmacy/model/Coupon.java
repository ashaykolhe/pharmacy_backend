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
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @Column(nullable = false)
    private LocalDateTime validTill;
    @Column(nullable = false)
    private Integer numberOfUses;
    @Column(nullable = false)
    private Boolean isValid;
    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;
}
