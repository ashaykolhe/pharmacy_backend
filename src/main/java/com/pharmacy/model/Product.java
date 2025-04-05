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
    private String name;
    private LocalDate manufactureDate;
    private LocalDate expiryDate;
    private String batchNumber;
    @OneToOne
    @JoinColumn(name = "product_company_id", referencedColumnName = "id")
    private ProductCompany productCompany;
    private Double purchaseRate;
    private Double saleRate;//private mrp/sellingPrice/cost
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductType productType;
    private Boolean inStock;
    private Double tax;
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
    private String genericName;
    private Shelf shelf;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quantity_of_product_id", referencedColumnName = "id")
    private QuantityOfProduct quantityOfProduct;
}
