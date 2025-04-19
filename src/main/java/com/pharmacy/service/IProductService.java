package com.pharmacy.service;

import com.pharmacy.model.Product;

import java.util.List;

public interface IProductService {
    void saveProduct(Product product);

    void saveProducts(List<Product> products);
}
