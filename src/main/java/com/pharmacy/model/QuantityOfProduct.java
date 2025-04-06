package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class QuantityOfProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 10)
    private Double numberOfPackets;
    @Column(nullable = false, length = 10)
    private Double numberOfStrips;
    @Column(nullable = false, length = 10)
    private Double quantityPerStrip;
    @Column(nullable = false, length = 10)
    private Double quantityOfNonMedicine;
}
