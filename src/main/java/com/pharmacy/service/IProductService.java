package com.pharmacy.service;

import com.pharmacy.dto.ProductDto;
import com.pharmacy.model.Product;

import java.util.List;

public interface IProductService {
    Product saveProduct(Product product);

    List<Product> saveProducts(List<Product> products);

    List<Product> fullTextSearch(String text, int offset, int limit);
    List<ProductDto> fetchProducts();
}
