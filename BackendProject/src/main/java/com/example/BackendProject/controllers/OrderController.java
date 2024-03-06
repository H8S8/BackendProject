package com.example.BackendProject.controllers;

import com.example.BackendProject.models.NewOrderDTO;
import com.example.BackendProject.models.Order;
import com.example.BackendProject.models.Supermarket;
import com.example.BackendProject.repositories.SupermarketRepository;
import com.example.BackendProject.services.OrderService;
import com.example.BackendProject.services.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    SupermarketService supermarketService;

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id){
        Optional<Order> order = orderService.getOrderById(id);
        if(order.isPresent()){
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Order> postOrder(@RequestBody NewOrderDTO newOrderDTO){
        Optional<Supermarket> supermarket = supermarketService.getSupermarketById(newOrderDTO.getSupermarketId());
        if(supermarket.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        Order newOrder = orderService.saveOrder(newOrderDTO);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Order> updateOrder(@RequestBody NewOrderDTO newOrderDTO, @PathVariable Long id){
        Optional<Order> orderToUpdate = orderService.getOrderById(id);
        if(orderToUpdate.isPresent()) {
             Order newOrder = orderService.updateOrder(newOrderDTO, id);
             return new ResponseEntity<>(newOrder, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteOrder(@PathVariable Long id){
        Optional<Order> orderToDelete = orderService.getOrderById(id);
        if(orderToDelete.isPresent()){
            orderService.deleteOrder(id);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


}
