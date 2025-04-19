package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.*;
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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    private Long id;
    @Column(nullable = false, length = 500)
    @FullTextField
    @KeywordField(name = "sortName", sortable = Sortable.YES)
    private String name;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductType productType;
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "products_suppliers", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
    private List<Supplier> suppliers;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
    private Manufacturer manufacturer;
    @Column(nullable = false, length = 500)
    @FullTextField
    @KeywordField(name = "sortGenericName", sortable = Sortable.YES)
    private String genericName;
    @Column(length = 5000, columnDefinition = "TEXT")
    private String description;
    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;
    @Column(length = 2000, columnDefinition = "TEXT")
    private String sideEffects;
}
