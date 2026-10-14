package com.techlab.presentation;

import com.techlab.application.ArticuloService;
import com.techlab.domain.model.Articulo;

import java.util.List;
import java.util.Scanner;


public class ArticuloMenu {

    private final ArticuloService articuloService;
    private final Scanner scanner;

    public ArticuloMenu(ArticuloService articuloService, Scanner scanner) {
        this.articuloService = articuloService;
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        // Implementación del menú de artículos
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú de Artículos ===");
            System.out.println("1. Agregar artículo");
            System.out.println("2. Listar artículos");
            System.out.println("3. Buscar artículo por nombre");
            System.out.println("4. Buscar artículo por ID");
            System.out.println("5. Ingresar stock a artículo");
            System.out.println("6. Eliminar artículo");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarArticulo();
                    break;
                case 2:
                    listarArticulos();
                    break;
                case 3:
                    buscarArticuloPorNombre();
                    break;
                case 4:
                    buscarArticuloPorId();
                    break;
                case 5:
                    ingresarStockArticulo();
                    break;
                case 6:
                    eliminarArticulo();
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void agregarArticulo() {
        System.out.print("Ingrese el nombre del artículo: ");
        String nombre = scanner.nextLine();
        
        //acá iría la verificación de entrada consola que sea un número válido para precio
        double precio = VerificacionConsola.leerDoublePositivo("Ingrese el precio del artículo: ", scanner);
      

        //acá iría la verificación de entrada consola que sea un número válido para stock
        int cantidadStock = VerificacionConsola.leerEnteroPositivo("Ingrese la cantidad de stock del artículo: ", scanner);

        
        boolean exito = articuloService.agregarArticulo(nombre, precio, cantidadStock);
        if (exito) {
            System.out.println("Artículo agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el artículo.");
        }
    }

    private void listarArticulos() {
        List<Articulo> articulos = articuloService.listarTodosLosArticulos();
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos registrados.");
        } else {
            System.out.println("=== Lista de Artículos ===");
            for (Articulo articulo : articulos) {
                System.out.println("ID: " + articulo.getId() + ", Nombre: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio());
            }
        }
    }

    private void buscarArticuloPorNombre() {
        String nombre = VerificacionConsola.leerTextoNoVacio("Ingrese el nombre del artículo a buscar: ", scanner);
        Articulo articulo = articuloService.buscarArticuloPorNombre(nombre);
        if (articulo != null) {
            System.out.println("Artículo encontrado: ID: " + articulo.getId() + ", Nombre: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio());
        } else {
            System.out.println("No se encontró un artículo con ese nombre.");
        }
    }

    private void buscarArticuloPorId() {
        int id = VerificacionConsola.leerEnteroPositivo("Ingrese el ID del artículo a buscar: ", scanner);
        Articulo articulo = articuloService.buscarArticuloPorId(id);
        if (articulo != null) {
            System.out.println("Artículo encontrado: ID: " + articulo.getId() + ", Nombre: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio());
        } else {
            System.out.println("No se encontró un artículo con ese ID.");
        }
    }

    private void ingresarStockArticulo() {
        //acá iría la verificación de entrada consola que sea un número válido para ID
        int id = VerificacionConsola.leerEnteroNoNegativo("Ingrese el ID del artículo al que desea ingresar stock: ", scanner);

        //acá iría la verificación de entrada consola que sea un número válido para stock
        int cantidad = VerificacionConsola.leerEnteroPositivo("Ingrese la cantidad de stock a ingresar: ", scanner);

        try {
            articuloService.ingresarStockArticulo(id, cantidad);
            System.out.println("Stock ingresado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarArticulo() {
        //acá iría la verificación de entrada consola que sea un número válido para ID
        int id = VerificacionConsola.leerEnteroNoNegativo("Ingrese el ID del artículo a eliminar: ", scanner);

        boolean exito = articuloService.eliminarArticulo(id);
        if (exito) {
            System.out.println("Artículo eliminado exitosamente.");
        } else {
            System.out.println("No se encontró un artículo con ese ID.");
        }
    }




}
