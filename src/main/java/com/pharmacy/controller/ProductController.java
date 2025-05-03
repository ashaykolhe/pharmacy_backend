package com.pharmacy.controller;

import com.pharmacy.aop.NoActivityLog;
import com.pharmacy.dto.ProductDto;
import com.pharmacy.model.Product;
import com.pharmacy.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final IProductService iProductService;

    @NoActivityLog
    @GetMapping("/v1/findAll")
    public ResponseEntity<List<ProductDto>> findAll() {
        return ResponseEntity.ok(iProductService.fetchProducts());
    }
}
