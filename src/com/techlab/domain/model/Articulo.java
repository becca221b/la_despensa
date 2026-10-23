package com.techlab.domain.model;

import com.techlab.domain.exceptions.StockInsuficienteException;
import com.techlab.utils.Validaciones;

public abstract class Articulo implements Calculable, Identificable {
    private int id;
    private String nombre;
    private double precio;
    private int cantidadStock;
    private Categoria categoria;

    public Articulo(int id, String nombre, double precio, Categoria categoria, int cantidadStock) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
        this.cantidadStock = cantidadStock;
    }

    public int getId() {
        return id;
    }

    @Override
    public int getCodigo() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void cambiarPrecio(double nuevoPrecio) {
        if (!Validaciones.validarPositivo(nuevoPrecio)) {
            throw new IllegalArgumentException("El precio debe ser mayor a cero.");
        }
        this.precio = nuevoPrecio;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public abstract String getTipoArticulo();

    protected abstract String getDetalleEspecifico();

    public void restarStock(int cantidad) throws StockInsuficienteException {
        if (!Validaciones.validarNoNegativo(cantidad)) {
            throw new IllegalArgumentException("La cantidad de stock no puede ser negativa.");
        }
        if (cantidad > cantidadStock) {
            throw new StockInsuficienteException(cantidad, cantidadStock);
        }
        this.cantidadStock -= cantidad;
    }

    public void ingresarStock(int cantidad) {
        if (!Validaciones.validarNoNegativo(cantidad)) {
            throw new IllegalArgumentException("La cantidad de stock no puede ser negativa.");
        }
        this.cantidadStock += cantidad;
    }

    @Override
    public String toString() {
        return "Articulo {" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidadStock=" + cantidadStock +
                ", categoria=" + categoria +
                ", tipo='" + getTipoArticulo() + '\'' +
                ", " + getDetalleEspecifico() +
                '}';
    }
}
