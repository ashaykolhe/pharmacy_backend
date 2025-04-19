package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Orders orders;
    @Column(nullable = false)
    private Integer quantity;
}
