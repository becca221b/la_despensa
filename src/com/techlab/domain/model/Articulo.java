package com.techlab.domain.model;

import com.techlab.domain.exceptions.StockInsuficienteException;

public class Articulo {
    private int id;
    private String nombre;
    private double precio;
    private int cantidadStock;

    
    
    public Articulo(int id, String nombre, double precio, int cantidadStock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = cantidadStock;
    }

    public Articulo(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidadStock = 0; // Inicializa el stock en 0 si no se proporciona
    }       

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    
    public double getPrecio() {
        return precio;
    }

    public void cambiarPrecio(double nuevoPrecio) {
        if (nuevoPrecio <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }
        this.precio = nuevoPrecio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void restarStock(int cantidad) throws StockInsuficienteException {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad de stock no puede ser negativa.");
        }
        if (cantidad > cantidadStock) {
            throw new StockInsuficienteException(cantidad, cantidadStock);
        }
        this.cantidadStock -= cantidad;
    }

    public void ingresarStock(int cantidad) {
        if (cantidad < 0) {
            throw new IllegalArgumentException("La cantidad de stock no puede ser negativa.");
        }
        this.cantidadStock += cantidad;
    }

    
    
}
