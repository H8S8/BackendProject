package com.example.BackendProject.controllers;

import com.example.BackendProject.models.Item;
import com.example.BackendProject.models.ProductType;
import com.example.BackendProject.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    //INDEX

    @GetMapping
    public ResponseEntity<List<Item>> getAllItems(
            @RequestParam(required = false, name = "productType")ProductType productType
            ){
        if (productType != null){
            return new ResponseEntity<>(itemService.findByProductType(productType), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
        }

    }
    //SHOW
    @GetMapping(value = "/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id){
        Optional<Item> itemFound = itemService.findItemById(id);
        if(itemFound.isPresent()){
            return new ResponseEntity<>(itemFound.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //CREATE
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item){
        Item createdItem = itemService.saveItem(item);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> deleteItem(@PathVariable Long id){
        itemService.deleteItem(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //UPDATE
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Item> updateItem(@RequestBody Item item, @PathVariable Long id){
        Optional<Item> itemToUpdate = itemService.findItemById(id);
        if(itemToUpdate.isPresent()) {
            Item updatedItem = itemService.updateItem(item, id);
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    }
}
