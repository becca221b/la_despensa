package com.techlab.presentation;

import java.util.Scanner;

public abstract class Menu {
    protected final Scanner scanner;

    protected Menu(Scanner scanner) {
        this.scanner = scanner;
    }

    public abstract void mostrarMenu();

    protected int leerEnteroNoNegativo(String mensaje) {
        return VerificacionConsola.leerEnteroNoNegativo(mensaje, scanner);
    }

    protected int leerEnteroPositivo(String mensaje) {
        return VerificacionConsola.leerEnteroPositivo(mensaje, scanner);
    }

    protected double leerDoublePositivo(String mensaje) {
        return VerificacionConsola.leerDoublePositivo(mensaje, scanner);
    }

    protected String leerTextoNoVacio(String mensaje) {
        return VerificacionConsola.leerTextoNoVacio(mensaje, scanner);
    }
}
