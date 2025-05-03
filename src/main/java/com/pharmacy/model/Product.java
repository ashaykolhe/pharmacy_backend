package com.pharmacy.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "product_type_id", nullable = false)
    private ProductType productType;
    @JsonManagedReference
    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "products_suppliers", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "supplier_id", referencedColumnName = "id"))
    private List<Supplier> suppliers;
    @JsonManagedReference
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manufacturer_id", referencedColumnName = "id", nullable = false)
    private Manufacturer manufacturer;
    @Column(nullable = false, length = 500)
    @FullTextField
    @KeywordField(name = "sortGenericName", sortable = Sortable.YES)
    private String genericName;
    @Column(length = 5000, columnDefinition = "TEXT")
    private String description;
    @JsonManagedReference
    @ToString.Exclude
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Stock> stocks;
    @Column(length = 2000, columnDefinition = "TEXT")
    private String sideEffects;
}
