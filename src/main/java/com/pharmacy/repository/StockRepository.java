package com.pharmacy.repository;

import com.pharmacy.model.Product;
import com.pharmacy.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findByBatchNumberAndExpiryDateAndProduct(String batchNumber, LocalDate expiryDate, Product product);
}
