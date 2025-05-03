package com.pharmacy.service;

import com.pharmacy.dto.ProductDto;
import com.pharmacy.model.Product;
import com.pharmacy.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.search.engine.search.sort.dsl.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.Arrays;
import java.util.List;

@Service
@Log4j2
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        log.debug("product saved " + product.getName());
        return productRepository.save(product);
    }

    @Override
    public List<Product> saveProducts(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Transactional(readOnly = true)
    public List<Product> search(String text, Integer pageNumber, Integer numberOfElements, String sortDir, String sortBy) {

        return productRepository
                .fullTextSearch(text, pageNumber, numberOfElements, List.of("name", "genericName"), sortBy, sortDir.equalsIgnoreCase("asc") ? SortOrder.ASC : SortOrder.DESC);
    }

    @Override
    public List<Product> fullTextSearch(String text, int offset, int limit) {
        return productRepository.fullTextSearch(text, offset, limit, Arrays.asList("name", "genericName"), "name", SortOrder.ASC);
    }

    @Override
    public List<ProductDto> fetchProducts() {
        PageRequest pageRequest = PageRequest.of(0, 100, Sort.by("name").ascending());
        return productRepository.fetchProducts(pageRequest);
    }
}
