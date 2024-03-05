package com.example.BackendProject.controllers;

import com.example.BackendProject.models.Order;
import com.example.BackendProject.models.Supermarket;
import com.example.BackendProject.services.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/supermarket")
public class SupermarketController {

    @Autowired
    SupermarketService supermarketService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Supermarket> getSupermarketById(@PathVariable long id){
        Optional<Supermarket> supermarket = supermarketService.getSupermarketById(id);
        if(supermarket.isPresent()){
            return new ResponseEntity<>(supermarket.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
