package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.example.demo.entities.ProductEntity;
import com.example.demo.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Obtener todos los productos
    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    // Obtener producto por ID
    public Optional<ProductEntity> getProductById(UUID id) {
        return productRepository.findById(id);
    }

    // Crear nuevo producto
    public ProductEntity createProduct(ProductEntity product) {
        product.setCreatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    // Actualizar producto existente
    public Optional<ProductEntity> updateProduct(UUID id, ProductEntity updatedProduct) {
        return productRepository.findById(id).map(existingProduct -> {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setBrand(updatedProduct.getBrand());
            existingProduct.setCategory(updatedProduct.getCategory());
            existingProduct.setModel(updatedProduct.getModel());
            existingProduct.setStock(updatedProduct.getStock());
            existingProduct.setSpecifications(updatedProduct.getSpecifications());
            existingProduct.setImageUrl(updatedProduct.getImageUrl());
            existingProduct.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(existingProduct);
        });
    }

    // Eliminar producto
    public boolean deleteProduct(UUID id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }
}