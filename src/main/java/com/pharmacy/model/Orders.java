package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Orders {//Receipt/Bill/Sale/Invoice
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id", nullable = false)
    private Employee employee;
    @CreationTimestamp
    private LocalDateTime createdTimestamp;
    @UpdateTimestamp
    private LocalDateTime modifiedTimestamp;
    @Column(nullable = false, length = 10)
    private Double total;
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<LineItem> lineItems;
    private double paidAmount;
    private double remainingAmount;
    @CreationTimestamp
    private LocalDateTime payDateTime;
    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToOne
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;
    private String comment;//notes
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @Column(nullable = false, unique = true, length = 10)
    private String orderNumber;
}
