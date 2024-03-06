package com.example.BackendProject.services;

import com.example.BackendProject.models.*;
import com.example.BackendProject.repositories.OrderRepository;
import com.example.BackendProject.repositories.OrderedItemRepository;
import com.example.BackendProject.repositories.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SupermarketRepository supermarketRepository;

    @Autowired
    OrderedItemRepository orderedItemRepository;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(long id){
        return orderRepository.findById(id);
    }

    public Order saveOrder(NewOrderDTO newOrderDTO) {
        Order order = new Order(OrderStatus.PENDING,supermarketRepository.findById(newOrderDTO.getSupermarketId()).get());
        orderRepository.save(order);
        return order;
    }


    public Order updateOrder(NewOrderDTO newOrderDTO, Long id) {
        Order orderToUpdate = orderRepository.findById(id).get();
        OrderStatus orderStatusToUpdate = newOrderDTO.getOrderStatus();
        orderToUpdate.setOrderStatus(orderStatusToUpdate);
        orderRepository.save(orderToUpdate);
        return orderToUpdate;
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id).get();
        for(OrderedItem orderedItem : order.getOrderedItem()){
            orderedItemRepository.deleteById(orderedItem.getId());
        }
        orderRepository.deleteById(id);
    }
}
