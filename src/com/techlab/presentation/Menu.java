package com.techlab.presentation;

import java.util.Scanner;

public class Menu {
    private final ArticuloMenu productoMenu;
    private final OrdenMenu ordenMenu;
    private final Scanner scanner;

    public Menu(ArticuloMenu productoMenu, OrdenMenu ordenMenu, Scanner scanner) {
        this.productoMenu = productoMenu;
        this.ordenMenu = ordenMenu;
        this.scanner = scanner;
    }

    public void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Menú de Productos");
            System.out.println("2. Menú de Órdenes");
            System.out.println("3. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    productoMenu.mostrarMenu();
                    break;
                case 2:
                    ordenMenu.mostrarMenu();
                    break;
                case 3:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}
