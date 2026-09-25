package com.smartERP.Backend.Services;

import com.smartERP.Backend.DTOs.StockDTO;
import com.smartERP.Backend.Entities.Stock;
import com.smartERP.Backend.Exceptions.ResourceNotFoundException;
import com.smartERP.Backend.Repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public List<StockDTO.StockResponse> getAllStockItems() {
        return stockRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    public StockDTO.StockResponse createStockItem(StockDTO.StockRequest stockRequest) {
        Stock stock = Stock.builder()
                .itemName(stockRequest.itemName())
                .quantity(stockRequest.quantity())
                .unitPrice(stockRequest.unitPrice())
                .reorderLevel(stockRequest.reorderLevel())
                .build();

        return mapToResponse(stockRepository.save(stock));
    }

    public StockDTO.StockResponse updateQuantity(Long id, Integer additionalQuantity) {
        Stock stock = stockRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stock not found with the Id: " + id));

        stock.setQuantity(stock.getQuantity() + additionalQuantity);
        return mapToResponse(stockRepository.save(stock));
    }

    public StockDTO.StockResponse mapToResponse(Stock stock) {
        return new StockDTO.StockResponse(
                stock.getId(),
                stock.getItemName(),
                stock.getQuantity(),
                stock.getUnitPrice(),
                stock.getReorderLevel());
    }
}