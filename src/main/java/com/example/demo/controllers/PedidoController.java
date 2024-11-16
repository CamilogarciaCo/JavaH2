package com.example.demo.controllers;

import com.example.demo.entities.Pedido;
import com.example.demo.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/activos")
    public List<Pedido> listarActivos() {
        return pedidoService.listarActivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtenerPorId(@PathVariable Long id) {
        return pedidoService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/bodega/{bodegaId}")
    public List<Pedido> buscarPorBodega(@PathVariable Long bodegaId) {
        return pedidoService.buscarPorBodega(bodegaId);
    }

    @GetMapping("/estado/{estado}")
    public List<Pedido> buscarPorEstado(@PathVariable String estado) {
        return pedidoService.buscarPorEstado(estado);
    }

    @GetMapping("/fecha")
    public List<Pedido> buscarPorFechas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fin) {
        return pedidoService.buscarPorFechas(inicio, fin);
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.crear(pedido);
            return ResponseEntity.ok(nuevoPedido);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> actualizar(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido pedidoActualizado = pedidoService.actualizar(id, pedido);
            return ResponseEntity.ok(pedidoActualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            pedidoService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente")
    public List<Pedido> buscarPorCliente(@RequestParam String nombre) {
        return pedidoService.buscarPorCliente(nombre);
    }
}