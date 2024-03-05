package com.example.BackendProject.repositories;

import com.example.BackendProject.models.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupermarketRepository extends JpaRepository<Supermarket, Long> {
}
