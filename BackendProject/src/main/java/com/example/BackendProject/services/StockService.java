package com.example.BackendProject.services;

import com.example.BackendProject.models.Item;
import com.example.BackendProject.models.NewStockDTO;
import com.example.BackendProject.models.Stock;
import com.example.BackendProject.repositories.ItemRepository;
import com.example.BackendProject.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockService {
    @Autowired
    StockRepository stockRepository;
    @Autowired
    ItemRepository itemRepository;

    public List<Stock> findAllStock(){
        return stockRepository.findAll();
    }
    public Stock findStock(long id){
        return stockRepository.findById(id).get();
    }
    public Stock saveStock(NewStockDTO newStockDTO){
        Stock stock = new Stock(itemRepository.findById(newStockDTO.getItemId()).get(),newStockDTO.getQuantity(), newStockDTO.getExpiryDate());
        return stockRepository.save(stock);


    }


}
