package com.pharmacy.service;

import com.pharmacy.model.Manufacturer;
import com.pharmacy.model.Product;

import java.util.List;
import java.util.Optional;

public interface IManufacturerService {
    Manufacturer saveManufacturer(Manufacturer manufacturer);

    List<Manufacturer> saveManufacturers(List<Manufacturer> manufacturers);
    Optional<Manufacturer> findByName(String name);
}
