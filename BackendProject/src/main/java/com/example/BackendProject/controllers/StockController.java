package com.example.BackendProject.controllers;

import com.example.BackendProject.models.NewStockDTO;
import com.example.BackendProject.models.Stock;
import com.example.BackendProject.services.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("stock")
public class StockController {

    @Autowired
    StockService stockService;

    @GetMapping
    public ResponseEntity<List<Stock>> getAllStock(){
        return new ResponseEntity<>(stockService.findAllStock(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Stock> getStock(@PathVariable Long id){
        Optional<Stock> stockOptional = stockService.findStock(id);
        if(stockOptional.isPresent()) {
            return new ResponseEntity<>(stockService.findStock(id).get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
        
    @PostMapping
    public ResponseEntity<Stock> postStock(@RequestBody NewStockDTO newStockDTO){
        Stock stock = stockService.saveStock(newStockDTO);
        return new ResponseEntity<>(stock, HttpStatus.CREATED);
    }





}
