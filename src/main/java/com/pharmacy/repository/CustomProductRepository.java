package com.pharmacy.repository;

import com.pharmacy.dto.ProductDto;
import com.pharmacy.model.Product;
import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;

public interface CustomProductRepository {
    List<ProductDto> fetchProducts(PageRequest pageRequest);
}
