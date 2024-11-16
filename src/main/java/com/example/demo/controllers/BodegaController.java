package com.example.demo.controllers;

import com.example.demo.entity.Bodega;
import com.example.demo.services.BodegaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bodegas")
public class BodegaController {

    @Autowired
    private BodegaService bodegaService;

    @GetMapping
    public List<Bodega> listarTodas() {
        return bodegaService.listarTodas();
    }

    @GetMapping("/activas")
    public List<Bodega> listarActivas() {
        return bodegaService.listarActivas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bodega> obtenerPorId(@PathVariable Long id) {
        return bodegaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Bodega crear(@RequestBody Bodega bodega) {
        return bodegaService.crear(bodega);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bodega> actualizar(@PathVariable Long id, @RequestBody Bodega bodega) {
        try {
            Bodega bodegaActualizada = bodegaService.actualizar(id, bodega);
            return ResponseEntity.ok(bodegaActualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            bodegaService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar")
    public List<Bodega> buscarPorNombre(@RequestParam String nombre) {
        return bodegaService.buscarPorNombre(nombre);
    }
}