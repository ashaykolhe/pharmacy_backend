package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class OnlineDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(length = 10)
    private Double packagingCost;//deliveryCharges
    @OneToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private Courier courier;
    @Column(unique = true)
    private String trackingId;
    private Double courierCost;
    private Double totalCost;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;
    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id", nullable = false)
    private Orders orders;
    private String prescription;// - upload photo
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;// - no cod
}
