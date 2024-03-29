package com.example.BackendProject.services;

import com.example.BackendProject.Exceptions.NotEnoughStockException;
import com.example.BackendProject.Exceptions.OrderedCantBeUpdatedException;
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
            throw new NotEnoughStockException("Not enough stock");
        }

        if(order.getOrderStatus() == OrderStatus.OUT_FOR_DELIVERY || order.getOrderStatus() == OrderStatus.DELIVERED){
            throw new OrderedCantBeUpdatedException("Can't add items to order");
        }

        OrderedItem orderedItem = new OrderedItem(orderRepository.findById(newOrderedItemDTO.getOrderId()).get(),
                stock, newOrderedItemDTO.getOrderQuantity());

        stock.removeFromStock(newOrderedItemDTO.getOrderQuantity());
        stockRepository.save(stock);
        orderedItemRepository.save(orderedItem);
        return orderedItem;
    }


    @Transactional
    public OrderedItem updateOrderedItem(NewOrderedItemDTO newOrderedItemDTO, Long id) throws Exception{
        OrderedItem orderedItemToUpdate = orderedItemRepository.findById(id).get();
        Stock stock = orderedItemToUpdate.getStock();
        Order order = orderedItemToUpdate.getOrder();

        if (newOrderedItemDTO.getOrderQuantity() > (stock.getQuantity() + orderedItemToUpdate.getOrderQuantity())){
            throw new NotEnoughStockException("Not enough stock");
        }

        if (order.getOrderStatus() == OrderStatus.OUT_FOR_DELIVERY || order.getOrderStatus() == OrderStatus.DELIVERED){
            throw new OrderedCantBeUpdatedException("Can't add items to order");
        }

        stock.removeFromStock(newOrderedItemDTO.getOrderQuantity() - orderedItemToUpdate.getOrderQuantity());
        orderedItemToUpdate.setOrderQuantity(newOrderedItemDTO.getOrderQuantity());
        stockRepository.save(stock);
        orderedItemRepository.save(orderedItemToUpdate);
        return orderedItemToUpdate;
    }
}
