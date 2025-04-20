package com.pharmacy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50, unique = true)
    private String fileName;
    @Column(nullable = false, length = 50)
    private String displayName;
    //path should come from properties
    private String dimension;
    private String size;
}
