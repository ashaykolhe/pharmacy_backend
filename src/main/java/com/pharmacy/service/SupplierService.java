package com.pharmacy.service;

import com.pharmacy.model.Supplier;
import com.pharmacy.repository.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class SupplierService implements ISupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    public void saveSupplier(Supplier supplier) {
        log.debug("supplier saved " + supplier.getName());
        supplierRepository.save(supplier);
    }

    @Override
    public void saveSuppliers(List<Supplier> suppliers) {
        suppliers.forEach(this::saveSupplier);
    }

    @Override
    public Optional<Supplier> findByName(String name) {
        log.debug("find supplier " + name);
        return supplierRepository.findByName(name);
    }
}
