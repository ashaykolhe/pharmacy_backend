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
public class Supplier {//Distributor/Vendor
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(length = 50)
    private String middleName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;
    private Boolean active;
    @ManyToMany(mappedBy = "suppliers")
    private List<Product> products;
    @Column(nullable = false, length = 20, unique = true)
    private String gstin;
}
