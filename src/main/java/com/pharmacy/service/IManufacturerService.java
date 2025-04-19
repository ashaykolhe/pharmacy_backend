package com.pharmacy.service;

import com.pharmacy.model.Manufacturer;
import com.pharmacy.model.Product;

import java.util.List;
import java.util.Optional;

public interface IManufacturerService {
    void saveManufacturer(Manufacturer manufacturer);

    void saveManufacturers(List<Manufacturer> manufacturers);
    Optional<Manufacturer> findByName(String name);
}
