package com.example.demo.services;

import com.example.demo.entity.Bodega;
import com.example.demo.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BodegaService {

    @Autowired
    private BodegaRepository bodegaRepository;

    public List<Bodega> listarTodas() {
        return bodegaRepository.findAll();
    }

    public List<Bodega> listarActivas() {
        return bodegaRepository.findByActivoTrue();
    }

    public Optional<Bodega> obtenerPorId(Long id) {
        return bodegaRepository.findById(id);
    }

    public Bodega crear(Bodega bodega) {
        return bodegaRepository.save(bodega);
    }

    public Bodega actualizar(Long id, Bodega bodega) {
        Optional<Bodega> bodegaExistente = bodegaRepository.findById(id);
        if (bodegaExistente.isPresent()) {
            bodega.setId(id);
            return bodegaRepository.save(bodega);
        }
        throw new RuntimeException("Bodega no encontrada con ID: " + id);
    }

    public void eliminar(Long id) {
        Optional<Bodega> bodega = bodegaRepository.findById(id);
        if (bodega.isPresent()) {
            Bodega bodegaExistente = bodega.get();
            bodegaExistente.setActivo(false);
            bodegaRepository.save(bodegaExistente);
        } else {
            throw new RuntimeException("Bodega no encontrada con ID: " + id);
        }
    }

    public List<Bodega> buscarPorNombre(String nombre) {
        return bodegaRepository.findByNombreContainingIgnoreCase(nombre);
    }
}