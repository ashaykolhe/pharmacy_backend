package com.pharmacy.service;

import com.pharmacy.model.Stock;
import com.pharmacy.repository.StockRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class StockService implements IStockService {
    private final StockRepository stockRepository;

    @Override
    public Stock saveStock(Stock stock) {
        log.debug("stock saved " + stock);
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> saveStocks(List<Stock> stocks) {
        return stockRepository.saveAll(stocks);
    }
}
