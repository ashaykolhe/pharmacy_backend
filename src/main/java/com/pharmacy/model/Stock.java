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
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private LocalDate manufactureDate;
    @Column(nullable = false)
    private LocalDate expiryDate;
    @Column(nullable = false, length = 50)
    private String batchNumber;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer quantity;
    @Column(nullable = false)
    private Boolean inStock;
    @Column(nullable = false, length = 200)
    private String dosage;
    @ManyToOne
    @JoinColumn(name = "tax_id", referencedColumnName = "id", nullable = false)
    private Tax tax;
    @Column(nullable = false, length = 10)
    private Double purchaseRate;
    @Column(nullable = false, length = 10)
    private Double saleRate;//private mrp/sellingPrice/cost
    @Column(length = 10)
    private Double profit;
    @Column(nullable = false, length = 10)
    private Double baseRate;
    @Enumerated(EnumType.STRING)
    private EShelf shelf;
    @Column(nullable = false)
    private Boolean isDiscontinued;

}
