package com.example.BackendProject.services;

import com.example.BackendProject.models.*;
import com.example.BackendProject.repositories.OrderRepository;
import com.example.BackendProject.repositories.OrderedItemRepository;
import com.example.BackendProject.repositories.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderedItemService {
    @Autowired
    OrderedItemRepository orderedItemRepository;
    
    @Autowired
    OrderRepository orderRepository;
    
    @Autowired
    StockRepository stockRepository;
    

    public List<OrderedItem> findAllOrderedItems() {
        return orderedItemRepository.findAll();
    }

    public Optional<OrderedItem> findOrderedItem(Long id) {
        return orderedItemRepository.findById(id);
    }

    public void deleteOrderedItem(Long id) {
        orderedItemRepository.deleteById(id);
    }

    @Transactional
    public OrderedItem saveOrderedItem(NewOrderedItemDTO newOrderedItemDTO)throws Exception{
        Stock stock = stockRepository.findById(newOrderedItemDTO.getStockId()).get();
        Order order = orderRepository.findById(newOrderedItemDTO.getOrderId()).get();

        if(newOrderedItemDTO.getOrderQuantity() > stock.getQuantity()){
            throw new Exception("Not enough stock");
        }

        if(order.getOrderStatus() != OrderStatus.PENDING || order.getOrderStatus() != OrderStatus.IN_PROGRESS){
            throw new Exception("Can't add items to order");
        }

        OrderedItem orderedItem = new OrderedItem(orderRepository.findById(newOrderedItemDTO.getOrderId()).get(),
                stock, newOrderedItemDTO.getOrderQuantity());

        stock.removeFromStock(newOrderedItemDTO.getOrderQuantity());
        stockRepository.save(stock);
        orderedItemRepository.save(orderedItem);
        return orderedItem;
    }


    public OrderedItem updateOrderedItem(NewOrderedItemDTO newOrderedItemDTO, Long id) {
        OrderedItem orderedItemToUpdate = orderedItemRepository.findById(id).get();
        orderedItemToUpdate.setOrderQuantity(newOrderedItemDTO.getOrderQuantity());
        orderedItemRepository.save(orderedItemToUpdate);
        return orderedItemToUpdate;
    }
}
