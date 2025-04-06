package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class ContactDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 5)
    private Integer mobileCountryCode;
    @Column(nullable = false, length = 10)
    private String mobile1;
    @Column(length = 10)
    private String mobile2;
    @Column(length = 50)
    private String email;
    private Boolean isMobile1Whatsapp;
    private Boolean isMobile2Whatsapp;
    @Column(length = 20)
    private String telephone;
}
