package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Employee {//Users
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
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @Column(nullable = false, length = 20)
    private String password;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payroll_id", referencedColumnName = "id")
    private Payroll payroll;
    @Column(nullable = false)
    private LocalDate dateOfBirth;
    @CreationTimestamp
    private LocalDateTime createdTimestamp;
    @UpdateTimestamp
    private LocalDateTime modifiedTimestamp;
    @Column(nullable = false, length = 20, unique = true)
    private String userName;
    @Enumerated(EnumType.STRING)
    private PersonalDocument document;
    @Column(nullable = false, length = 20)
    private String personalDocumentNumber;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", referencedColumnName = "id")
    private Photo photo;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"))
    private List<Role> roles;
    private String pharmacyLicenceNumber;
    private Boolean active;
    private Boolean accountLocked;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_detail_id", referencedColumnName = "id", nullable = false)
    private ContactDetail contactDetail;
}
