package com.example.BackendProject.services;

import com.example.BackendProject.models.Supermarket;
import com.example.BackendProject.repositories.SupermarketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SupermarketService {

    @Autowired
    SupermarketRepository supermarketRepository;

    public Optional<Supermarket> getSupermarketById(long id) {
        return supermarketRepository.findById(id);
    }
}
