package com.pharmacy.service;

import com.pharmacy.model.Tax;

import java.util.List;

public interface ITaxService {
    Tax saveTax(Tax tax);

    List<Tax> saveTaxes(List<Tax> taxes);
}
