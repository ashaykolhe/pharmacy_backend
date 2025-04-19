package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 500)
    private String line1;
    @Column(length = 500)
    private String line2;
    @Column(nullable = false, length = 10)
    private String pincode;
    @Column(nullable = false, length = 50)
    private String city;
    @Column(nullable = false, length = 20)
    private String state;
    @Column(nullable = false, length = 20)
    private String country;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_detail_id", referencedColumnName = "id")
    private ContactDetail contactDetail;
    private Boolean isDefault;
}
