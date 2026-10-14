package com.techlab.domain.model;

import java.util.List;
import java.util.ArrayList;

public class Orden {
    private int id;
    private String fecha;
    //private String estado;
    private List<DetalleOrden> pedidos;
  
    // Constructor
    public Orden( int id) {
        this.id = id;
        this.fecha = java.time.LocalDate.now().toString();
        //this.estado = estado;
        this.pedidos = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }
    /*
    public String getEstado() {
        return estado;
    }
    */
    

    public List<DetalleOrden> getDetalles() {
        return pedidos;
    }

    public double getCostoTotal() {
        return calcularCostoTotal();
    }

    public void agregarPedido(DetalleOrden pedido) {
        pedidos.add(pedido);
    }

    

    // Método para calcular el costo total de la orden
    public double calcularCostoTotal() {
        return pedidos.stream()
        .mapToDouble(DetalleOrden::calcularSubtotal)
        .sum();
    }

    

}


