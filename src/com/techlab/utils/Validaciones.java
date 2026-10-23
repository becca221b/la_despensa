package com.techlab.utils;

public final class Validaciones {
    private Validaciones() {
    }

    public static boolean validarTextoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public static boolean validarPositivo(int valor) {
        return valor > 0;
    }

    public static boolean validarPositivo(double valor) {
        return valor > 0;
    }

    public static boolean validarNoNegativo(int valor) {
        return valor >= 0;
    }

    public static boolean validarNoNegativo(double valor) {
        return valor >= 0;
    }
}