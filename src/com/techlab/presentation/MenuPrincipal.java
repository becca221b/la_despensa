package com.techlab.presentation;

import java.util.Scanner;

public class MenuPrincipal {
    private final ArticuloMenu articuloMenu;
    private final CategoriaMenu categoriaMenu;
    private final OrdenMenu ordenMenu;
    private final Scanner scanner;

    public MenuPrincipal(ArticuloMenu articuloMenu, CategoriaMenu categoriaMenu,
            OrdenMenu ordenMenu, Scanner scanner) {
        this.articuloMenu = articuloMenu;
        this.categoriaMenu = categoriaMenu;
        this.ordenMenu = ordenMenu;
        this.scanner = scanner;
    }

    public void mostrarMenuPrincipal() {
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Menú de Productos");
            System.out.println("2. Menú de Órdenes");
            System.out.println("3. Menú de Categorías");
            System.out.println("4. Salir");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    articuloMenu.mostrarMenu();
                    break;
                case 2:
                    ordenMenu.mostrarMenu();
                    break;
                case 3:
                    categoriaMenu.mostrarMenu();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }
}