package com.pharmacy.service;

import com.pharmacy.model.Manufacturer;
import com.pharmacy.repository.ManufacturerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class ManufacturerService implements IManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    @Override
    public void saveManufacturer(Manufacturer manufacturer) {
        log.debug("manufacturer saved " + manufacturer.getName());
        manufacturerRepository.save(manufacturer);
    }

    @Override
    public void saveManufacturers(List<Manufacturer> manufacturers) {
        manufacturers.forEach(this::saveManufacturer);
    }

    @Override
    public Optional<Manufacturer> findByName(String name) {
        log.debug("find manufacturer " + name);
        return manufacturerRepository.findByName(name);
    }
}
