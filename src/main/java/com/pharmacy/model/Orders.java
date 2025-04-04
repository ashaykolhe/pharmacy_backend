package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;
    private LocalDateTime createdTimestamp;
    private LocalDateTime modifiedTimestamp;
    private Double total;
    @OneToMany(mappedBy = "orders")
    private List<LineItem> lineItems;
    private double paidAmount;
    private double remainingAmount;
    private LocalDateTime payDateTime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quantity_of_product_id", referencedColumnName = "id")
    private QuantityOfProduct quantityOfProduct;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "offer_id", referencedColumnName = "id")
    private Offer offer;
    private String comment;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
}
