package com.techlab.presentation;

import com.techlab.application.OrdenService;
import java.util.Scanner;

public class OrdenMenu {
    private final OrdenService ordenService;
    private final Scanner scanner;

    public OrdenMenu(OrdenService ordenService, Scanner scanner) {
        this.ordenService = ordenService;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        // Implementación del menú de órdenes
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú de Órdenes ===");
            System.out.println("1. Crear nueva orden");
            System.out.println("2. Listar órdenes");
            System.out.println("3. Buscar orden por ID");
            System.out.println("4. Agregar artículo a orden");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    crearNuevaOrden();
                    break;
                case 2:
                    listarOrdenes();
                    break;

                case 3:
                    buscarOrdenPorId();
                    break;
                case 4:
                    agregarArticuloAOrden();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void crearNuevaOrden() {
        ordenService.crearOrden();
        System.out.println("Orden creada exitosamente.");
    }

    private void listarOrdenes() {
        System.out.println("=== Lista de Órdenes ===");
        ordenService.obtenerTodasLasOrdenes().forEach(orden -> {
            System.out.println("ID: " + orden.getId());
            System.out.println("Detalles: " );
            orden.getDetalles().forEach(detalle -> {
                System.out.println("Artículo: " + detalle.getArticulo().getNombre() + ", Cantidad: " + detalle.getCantidad());
            });
            System.out.println("Total: " + orden.calcularCostoTotal());
            System.out.println("------------------------");
        });
    }

    private void agregarArticuloAOrden() {
        
        int idOrden = VerificacionConsola.leerEnteroNoNegativo("Ingrese el ID de la orden: ", scanner);

        
        int idArticulo = VerificacionConsola.leerEnteroNoNegativo("Ingrese el ID del artículo: ", scanner);

        
        int cantidad = VerificacionConsola.leerEnteroPositivo("Ingrese la cantidad: ", scanner);

        try {
            ordenService.agregarArticuloAOrden(idOrden, idArticulo, cantidad);
            System.out.println("Artículo agregado a la orden exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void buscarOrdenPorId() {
        int idOrden = VerificacionConsola.leerEnteroNoNegativo("Ingrese el ID de la orden: ", scanner);

        try {
            var orden = ordenService.obtenerOrdenPorId(idOrden);
            System.out.println("ID: " + orden.getId());
            System.out.println("Detalles: " + orden.getDetalles());
            System.out.println("Total: " + orden.calcularCostoTotal());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}


