package com.example.BackendProject.controllers;

import com.example.BackendProject.models.*;
import com.example.BackendProject.services.OrderService;
import com.example.BackendProject.services.OrderedItemService;
import com.example.BackendProject.services.StockService;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ordered-items")
public class OrderedItemController {
    @Autowired
    OrderedItemService orderedItemService;
    @Autowired
    OrderService orderService;
    @Autowired
    StockService stockService;

    @GetMapping
    public ResponseEntity<List<OrderedItem>> getAllOrderedItems(){
        return new ResponseEntity<>(orderedItemService.findAllOrderedItems(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderedItem> getOrderedItem(@PathVariable Long id){
        Optional<OrderedItem> orderedItemFound = orderedItemService.findOrderedItem(id);
        if (orderedItemFound.isPresent()) {
            return new ResponseEntity<>(orderedItemFound.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<OrderedItem> postOrderedItem(@RequestBody NewOrderedItemDTO newOrderedItemDTO){
        Optional<Order> orderOptional  = orderService.getOrderById(newOrderedItemDTO.getOrderId());
        if(orderOptional.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Optional<Stock> optionalStock = stockService.findStock(newOrderedItemDTO.getStockId());
        if(optionalStock.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        try{

            OrderedItem orderedItem = orderedItemService.saveOrderedItem(newOrderedItemDTO);
            return new ResponseEntity<>(orderedItem, HttpStatus.CREATED);
        } catch(Exception exception){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<OrderedItem> updateOrderedItem(@RequestBody NewOrderedItemDTO newOrderedItemDTO,
                                                         @PathVariable Long id){
        Optional<OrderedItem> orderedItemToUpdate = orderedItemService.findOrderedItem(id);
        if (orderedItemToUpdate.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        try{
            OrderedItem newOrderedItem = orderedItemService.updateOrderedItem(newOrderedItemDTO, id);
            return new ResponseEntity<>(newOrderedItem, HttpStatus.OK);
        } catch(Exception exception){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteOrderedItem(@PathVariable Long id){
        orderedItemService.deleteOrderedItem(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
