package com.pharmacy.service;

import com.pharmacy.model.Stock;

import java.util.List;

public interface IStockService {
    void saveStock(Stock stock);

    void saveStocks(List<Stock> stocks);
}
