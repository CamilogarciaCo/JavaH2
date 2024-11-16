package com.example.demo.repository;

import com.example.demo.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByActivoTrue();
    List<Pedido> findByBodegaId(Long bodegaId);
    List<Pedido> findByEstado(String estado);
    List<Pedido> findByFechaPedidoBetween(LocalDateTime inicio, LocalDateTime fin);
    List<Pedido> findByClienteNombreContainingIgnoreCase(String nombre);
}