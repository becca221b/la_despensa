package com.techlab.domain.model;

import java.util.List;

public class Orden {
    private int id;
    private String fecha;
    //private String estado;
    private List<Pedido> pedidos;
  
    // Constructor
    public Orden( int id) {
        this.id = id;
        this.fecha = java.time.LocalDate.now().toString();
        //this.estado = estado;
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
    

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public double getCostoTotal() {
        return calcularCostoTotal();
    }

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public List<Pedido> obtenerPedidos() {
        return pedidos;
    }

    // Método para calcular el costo total de la orden
    public double calcularCostoTotal() {
        return pedidos.stream()
        .mapToDouble(Pedido::calcularSubtotal)
        .sum();
    }

    

}


