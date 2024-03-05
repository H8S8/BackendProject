package com.example.BackendProject.services;

import com.example.BackendProject.models.OrderedItem;
import com.example.BackendProject.models.Stock;
import com.example.BackendProject.repositories.OrderedItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderedItemService {
    @Autowired
    OrderedItemRepository orderedItemRepository;
    @Autowired
    StockService stockService;
    @Autowired
    OrderService orderService;

    public List<OrderedItem> findAllOrderedItems() {
        return orderedItemRepository.findAll();
    }

    public Optional<OrderedItem> findOrderedItem(Long id) {
        return orderedItemRepository.findById(id);
    }

    public void deleteOrderedItem(Long id) {
        orderedItemRepository.deleteById(id);
    }
}
