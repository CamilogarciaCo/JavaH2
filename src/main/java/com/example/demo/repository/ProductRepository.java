package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.example.demo.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}