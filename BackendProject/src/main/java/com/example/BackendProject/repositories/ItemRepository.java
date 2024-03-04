package com.example.BackendProject.repositories;

import com.example.BackendProject.models.Item;
import com.example.BackendProject.models.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByProductType(ProductType productType);

    
}
