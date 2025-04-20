package com.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String offerName;
    @ManyToMany
    @JoinTable(name = "offers_products", joinColumns = @JoinColumn(name = "offer_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    private List<Product> products;
    @Column(nullable = false, length = 10)
    private String discount;
    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    private List<Coupon> coupons;
}
