package com.techlab.domain.model;

public class ArticuloElectronico extends Articulo {
    private int garantiaMeses;

    public ArticuloElectronico(int id, String nombre, double precio, Categoria categoria,
            int cantidadStock, int garantiaMeses) {
        super(id, nombre, precio, categoria, cantidadStock);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    @Override
    public double calcularPrecioFinal() {
        return garantiaMeses > 12 ? getPrecio() * 1.10 : getPrecio();
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipoArticulo() {
        return "Electronico";
    }

    @Override
    protected String getDetalleEspecifico() {
        return "garantia=" + garantiaMeses + " meses";
    }
}