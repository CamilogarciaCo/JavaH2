package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import com.example.demo.entities.ProductEntity;
import com.example.demo.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById(@PathVariable UUID id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear nuevo producto
    @PostMapping
    public ResponseEntity<ProductEntity> createProduct(@RequestBody ProductEntity product) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.createProduct(product));
    }

    // Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct(@PathVariable UUID id,
                                                       @RequestBody ProductEntity product) {
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        return productService.deleteProduct(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}