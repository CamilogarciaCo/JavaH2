package com.example.demo.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import com.example.demo.entity.Bodega;


@Entity
@Table(name = "pedidos")
@Data
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bodega_id", nullable = false)
    private Bodega bodega;

    @Column(nullable = false)
    private String numeroOrden;

    @Column(nullable = false)
    private LocalDateTime fechaPedido;

    @Column
    private String estado;

    @Column
    private String clienteNombre;

    @Column
    private String clienteEmail;

    @Column
    private String clienteTelefono;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetallePedido> detalles;

    @Column(nullable = false)
    private Boolean activo = true;
}