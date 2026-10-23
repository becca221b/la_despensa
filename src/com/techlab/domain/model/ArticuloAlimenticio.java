package com.techlab.domain.model;

public class ArticuloAlimenticio extends Articulo {
    private int diasParaVencimiento;

    public ArticuloAlimenticio(int id, String nombre, double precio, Categoria categoria,
            int cantidadStock, int diasParaVencimiento) {
        super(id, nombre, precio, categoria, cantidadStock);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }

    @Override
    public double calcularPrecioFinal() {
        if (diasParaVencimiento <= 3) {
            return getPrecio() * 0.80;
        }
        if (diasParaVencimiento <= 7) {
            return getPrecio() * 0.90;
        }
        return getPrecio();
    }

    public void setDiasParaVencimiento(int diasParaVencimiento) {
        this.diasParaVencimiento = diasParaVencimiento;
    }

    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    @Override
    protected String getDetalleEspecifico() {
        return "dias para vencimiento=" + diasParaVencimiento;
    }
}