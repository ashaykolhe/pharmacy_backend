package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.search.engine.backend.types.Sortable;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.DocumentId;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.KeywordField;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Indexed
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    @FullTextField
    @KeywordField(name = "sortName", sortable = Sortable.YES)
    private String name;
    @Column(nullable = false, length = 10)
    private Double purchaseRate;
    @Column(nullable = false, length = 10)
    private Double saleRate;//private mrp/sellingPrice/cost
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductType productType;
    @Column(nullable = false, length = 10)
    private Double tax;
    @Column(length = 10)
    private Double profit;
    @Column(nullable = false, length = 10)
    private Double baseRate;
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "products_suppliers", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
    private List<Supplier> suppliers;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id")
    private Manufacturer manufacturer;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
    private List<Photo> photos;
    @Column(nullable = false, length = 30)
    @FullTextField
    @KeywordField(name = "sortGenericName", sortable = Sortable.YES)
    private String genericName;
    @Enumerated(EnumType.STRING)
    private Shelf shelf;
    @Column(length = 5000, columnDefinition = "TEXT")
    private String description;
    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;
}
