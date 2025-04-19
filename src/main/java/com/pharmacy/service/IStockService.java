package com.pharmacy.service;

import com.pharmacy.model.Stock;

import java.util.List;

public interface IStockService {
    Stock saveStock(Stock stock);

    List<Stock> saveStocks(List<Stock> stocks);
}
