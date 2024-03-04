package com.example.BackendProject.services;

import com.example.BackendProject.models.Item;
import com.example.BackendProject.models.ProductType;
import com.example.BackendProject.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findByProductType(ProductType productType) {
        return itemRepository.findByProductType(productType);
    }
}
