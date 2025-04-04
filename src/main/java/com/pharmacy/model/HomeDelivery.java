package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class HomeDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    Charges as per porter
    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Orders orders;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;// - no COD
    @OneToOne
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;
}
