package com.pharmacy.service;

import com.pharmacy.model.ProductType;

import java.util.List;

public interface IProductTypeService {
    void saveProductType(ProductType product);

    void saveProductTypes(List<ProductType> products);
}
