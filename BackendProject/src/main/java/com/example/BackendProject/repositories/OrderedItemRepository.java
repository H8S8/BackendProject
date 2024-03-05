package com.example.BackendProject.repositories;

import com.example.BackendProject.models.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedItemRepository extends JpaRepository <OrderedItem, Long> {

}
