package com.pharmacy.service;

import com.pharmacy.model.ProductType;

import java.util.List;

public interface IProductTypeService {
    ProductType saveProductType(ProductType product);

    List<ProductType> saveProductTypes(List<ProductType> products);
}
