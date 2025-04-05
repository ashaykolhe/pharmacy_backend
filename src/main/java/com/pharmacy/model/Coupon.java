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
    private LocalDateTime validTill;
    private Integer numberOfUses;
    private Boolean isValid;
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
}
