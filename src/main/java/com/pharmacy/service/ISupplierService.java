package com.pharmacy.service;

import com.pharmacy.model.Supplier;

import java.util.List;
import java.util.Optional;

public interface ISupplierService {
    void saveSupplier(Supplier supplier);

    void saveSuppliers(List<Supplier> suppliers);

    Optional<Supplier> findByName(String name);
}
