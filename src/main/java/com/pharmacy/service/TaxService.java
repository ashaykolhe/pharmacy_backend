package com.pharmacy.service;

import com.pharmacy.model.Tax;
import com.pharmacy.repository.TaxRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class TaxService implements ITaxService {
    private final TaxRepository taxRepository;

    @Override
    public Tax saveTax(Tax tax) {
        log.debug("tax saved " + tax);
        return taxRepository.save(tax);
    }

    @Override
    public List<Tax> saveTaxes(List<Tax> taxes) {
        return taxRepository.saveAll(taxes);
    }
}
