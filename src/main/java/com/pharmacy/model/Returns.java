package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Returns {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
    @ManyToMany
    @JoinTable(name = "returns_products", joinColumns = @JoinColumn(name = "return_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quantity_of_product_id", referencedColumnName = "id")
    private QuantityOfProduct quantityOfProduct;
}
