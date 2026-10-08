package com.techlab.domain.model;

public class Pedido {
    private int id;
    private Articulo articulo;
    private int cantidad;

    // Constructor
    public Pedido(Articulo articulo, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.articulo = articulo;
        this.cantidad = cantidad;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double calcularSubtotal() {
        return articulo.getPrecio() * cantidad;
    }
    

}
