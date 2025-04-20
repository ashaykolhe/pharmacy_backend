package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 100, unique = true)
    private String fileName;
    @Column(nullable = false, length = 100)
    private String displayName;
    @Enumerated(EnumType.STRING)
    private Type type;
    private String size;
}
