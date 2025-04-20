package com.pharmacy.service;

import com.pharmacy.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {
    Supplier saveSupplier(Supplier supplier);

    List<Supplier> saveSuppliers(List<Supplier> suppliers);

    Optional<Supplier> findByName(String name);
}
