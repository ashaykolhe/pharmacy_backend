package com.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class Supplier {//Distributor/Vendor
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @Column(nullable = false)
    private Boolean active;
    @JsonBackReference
    @ManyToMany(mappedBy = "suppliers")
    private List<Product> products;
    @Column(nullable = false, length = 50, unique = true)
    private String gstin;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_detail_id", referencedColumnName = "id", nullable = false)
    private ContactDetail contactDetail;
    @Column(nullable = false, length = 50, unique = true)
    private String licenseNumber;
}
