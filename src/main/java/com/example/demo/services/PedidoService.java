package com.example.demo.services;

import com.example.demo.entities.Pedido;
import com.example.demo.entities.DetallePedido;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.BodegaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BodegaRepository bodegaRepository;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public List<Pedido> listarActivos() {
        return pedidoRepository.findByActivoTrue();
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> buscarPorBodega(Long bodegaId) {
        return pedidoRepository.findByBodegaId(bodegaId);
    }

    public List<Pedido> buscarPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public List<Pedido> buscarPorFechas(LocalDateTime inicio, LocalDateTime fin) {
        return pedidoRepository.findByFechaPedidoBetween(inicio, fin);
    }

    @Transactional
    public Pedido crear(Pedido pedido) {
        // Validar que la bodega existe
        bodegaRepository.findById(pedido.getBodega().getId())
                .orElseThrow(() -> new RuntimeException("Bodega no encontrada"));

        // Establecer fecha si no está establecida
        if (pedido.getFechaPedido() == null) {
            pedido.setFechaPedido(LocalDateTime.now());
        }

        // Establecer estado inicial si es necesario
        if (pedido.getEstado() == null) {
            pedido.setEstado("PENDIENTE");
        }

        // Asociar el pedido con sus detalles
        if (pedido.getDetalles() != null) {
            pedido.getDetalles().forEach(detalle -> detalle.setPedido(pedido));
        }

        return pedidoRepository.save(pedido);
    }

    @Transactional
    public Pedido actualizar(Long id, Pedido pedido) {
        Optional<Pedido> pedidoExistente = pedidoRepository.findById(id);
        if (pedidoExistente.isPresent()) {
            pedido.setId(id);
            // Mantener la fecha original del pedido
            pedido.setFechaPedido(pedidoExistente.get().getFechaPedido());
            // Asociar el pedido con sus detalles
            if (pedido.getDetalles() != null) {
                pedido.getDetalles().forEach(detalle -> detalle.setPedido(pedido));
            }
            return pedidoRepository.save(pedido);
        }
        throw new RuntimeException("Pedido no encontrado con ID: " + id);
    }

    public void eliminar(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            Pedido pedidoExistente = pedido.get();
            pedidoExistente.setActivo(false);
            pedidoRepository.save(pedidoExistente);
        } else {
            throw new RuntimeException("Pedido no encontrado con ID: " + id);
        }
    }

    public List<Pedido> buscarPorCliente(String nombre) {
        return pedidoRepository.findByClienteNombreContainingIgnoreCase(nombre);
    }
}