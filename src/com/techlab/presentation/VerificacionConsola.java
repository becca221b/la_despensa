package com.techlab.presentation;

import java.util.Scanner;

import com.techlab.utils.Validaciones;

public class VerificacionConsola {

    private VerificacionConsola() {
        // Constructor privado para evitar instanciación
    }

    public static int leerEnteroNoNegativo(String mensaje, Scanner scanner) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("El valor no puede estar vacío.");
                continue;
            }
            try {
                int valor = Integer.parseInt(input);
                if (Validaciones.validarNoNegativo(valor)) {
                    return valor;
                }
                System.out.println("Ingrese un número entero no negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }

    public static int leerEnteroPositivo(String mensaje, Scanner scanner) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("El valor no puede estar vacío.");
                continue;
            }
            try {
                int valor = Integer.parseInt(input);
                if (Validaciones.validarPositivo(valor)) {
                    return valor;
                }
                //System.out.println("Ingrese un número entero positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
    }

    public static double leerDoublePositivo(String mensaje, Scanner scanner) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("El valor no puede estar vacío.");
                continue;
            }
            try {
                double valor = Double.parseDouble(input);
                if (Validaciones.validarPositivo(valor)) {
                    return valor;
                }
                System.out.println("Ingrese un número positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número válido.");
            }
        }
    }

    //verifica que el texto no esté vacío
    public static String leerTextoNoVacio(String mensaje, Scanner scanner) {
        while (true) {
            System.out.print(mensaje);
            String input = scanner.nextLine().trim();

            if (Validaciones.validarTextoNoVacio(input)) {
                return input;
            }
            System.out.println("El valor no puede estar vacío. Por favor, ingrese un texto válido.");
        }
    }
}
