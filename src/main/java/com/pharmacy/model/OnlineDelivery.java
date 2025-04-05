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
    private Double packagingCost;//deliveryCharges
    @OneToOne
    @JoinColumn(name = "courier_id", referencedColumnName = "id")
    private Courier courier;
    private String trackingId;
    private Double courierCost;
    private Double totalCost;
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
    @Enumerated(EnumType.STRING)
    private ShippingStatus shippingStatus;
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
    private String prescription;// - upload photo
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;// - no cod
}
