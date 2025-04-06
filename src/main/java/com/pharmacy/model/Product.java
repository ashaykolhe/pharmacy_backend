package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    @Column(nullable = false, length = 50)
    private String batchNumber;
    @OneToOne
    @JoinColumn(name = "product_company_id", referencedColumnName = "id")
    private ProductCompany productCompany;
    @Column(nullable = false, length = 10)
    private Double purchaseRate;
    @Column(nullable = false, length = 10)
    private Double saleRate;//private mrp/sellingPrice/cost
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductType productType;
    @Column(nullable = false)
    private Boolean inStock;
    @Column(nullable = false, length = 10)
    private Double tax;
    @Column(nullable = false, length = 10)
    private Double profit;
    @ManyToMany
    @JoinTable(name = "products_suppliers", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
    private List<Supplier> suppliers;
    @ManyToMany
    @JoinTable(name = "products_manufacturers",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "manufacturer_id", referencedColumnName = "id"))
    private List<Manufacturer> manufacturers;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;
    @Column(nullable = false, length = 10)
    private String genericName;
    private Shelf shelf;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quantity_of_product_id", referencedColumnName = "id", nullable = false)
    private QuantityOfProduct quantityOfProduct;
}
