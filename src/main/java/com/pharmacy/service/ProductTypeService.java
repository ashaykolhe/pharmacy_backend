package com.pharmacy.service;

import com.pharmacy.model.ProductType;
import com.pharmacy.repository.ProductTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class ProductTypeService implements IProductTypeService {
    private final ProductTypeRepository productTypeRepository;

    @Override
    public void saveProductType(ProductType productType) {
        log.debug("productType saved " + productType.getName());
        productTypeRepository.save(productType);
    }

    @Override
    public void saveProductTypes(List<ProductType> productTypes) {
        productTypes.forEach(this::saveProductType);
    }
}
