package com.pharmacy.service;

import com.pharmacy.model.Product;
import com.pharmacy.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        log.debug("product saved " + product.getName());
        productRepository.save(product);
    }

    @Override
    public void saveProducts(List<Product> products) {
        products.forEach(this::saveProduct);
    }

    @Transactional(readOnly = true)
    public List<Product> search(String text, Integer pageNumber, Integer numberOfElements, String sortDir, String sortBy) {

        return productRepository
                .fullTextSearch(text, pageNumber, numberOfElements, List.of("name", "genericName"), sortBy, sortDir.equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);
    }
}
