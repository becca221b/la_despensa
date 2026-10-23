package com.techlab.presentation;

import com.techlab.application.ArticuloService;
import com.techlab.application.CategoriaService;
import com.techlab.domain.model.Articulo;
import com.techlab.domain.model.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class ArticuloMenu extends Menu {

    private final ArticuloService articuloService;
    private final CategoriaService categoriaService;

    public ArticuloMenu(ArticuloService articuloService, Scanner scanner, CategoriaService categoriaService) {
        super(scanner);
        this.articuloService = articuloService;
        this.categoriaService = categoriaService;
    }

    @Override
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
            System.out.println("7. Modificar detalle específico");
            System.out.println("8. Salir");
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
                    modificarDetalleEspecifico();
                    break;
                case 8:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void agregarArticulo() {
        if (categoriaService.listarTodasLasCategorias().isEmpty()) {
            System.out.println("No hay categorías disponibles. Cree una categoría antes de agregar artículos.");
            return;
        }

        int tipo = seleccionarTipoArticulo();
        String nombre = leerTextoNoVacio("Ingrese el nombre del artículo: ");
        double precio = leerDoublePositivo("Ingrese el precio del artículo: ");
        int cantidadStock = leerEnteroPositivo("Ingrese la cantidad de stock del artículo: ");
        Categoria categoria = seleccionarCategoria();

        boolean exito;
        if (tipo == 1) {
            int garantiaMeses = leerEnteroNoNegativo("Ingrese los meses de garantía: ");
            exito = articuloService.agregarArticuloElectronico(
                nombre, precio, cantidadStock, categoria, garantiaMeses);
        } else {
            int diasParaVencimiento = leerEnteroNoNegativo("Ingrese los días para vencimiento: ");
            exito = articuloService.agregarArticuloAlimenticio(
                nombre, precio, cantidadStock, categoria, diasParaVencimiento);
        }

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
                System.out.println(articulo);
            }
        }
    }

    private int seleccionarTipoArticulo() {
        while (true) {
            System.out.println("Seleccione el tipo de artículo:");
            System.out.println("1. Electrónico");
            System.out.println("2. Alimenticio");
            int tipo = leerEnteroPositivo("Opción: ");
            if (tipo == 1 || tipo == 2) {
                return tipo;
            }
            System.out.println("Opción inválida. Intente nuevamente.");
        }
    }

    private Categoria seleccionarCategoria() {
        while (true) {
            System.out.println("=== Categorías ===");
            categoriaService.listarTodasLasCategorias().forEach(System.out::println);
            int codigo = leerEnteroPositivo("Ingrese el código de la categoría: ");
            Optional<Categoria> categoria = categoriaService.buscarCategoriaPorCodigo(codigo);
            if (categoria.isPresent()) {
                return categoria.get();
            }
            System.out.println("No existe una categoría con ese código.");
        }
    }

    private void buscarArticuloPorNombre() {
        String nombre = leerTextoNoVacio("Ingrese el nombre del artículo a buscar: ");
        Articulo articulo = articuloService.buscarArticuloPorNombre(nombre);
        if (articulo != null) {
            System.out.println("Artículo encontrado: ID: " + articulo.getId() + ", Nombre: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio());
        } else {
            System.out.println("No se encontró un artículo con ese nombre.");
        }
    }

    private void buscarArticuloPorId() {
        int id = leerEnteroPositivo("Ingrese el ID del artículo a buscar: ");
        Articulo articulo = articuloService.buscarArticuloPorId(id);
        if (articulo != null) {
            System.out.println("Artículo encontrado: ID: " + articulo.getId() + ", Nombre: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio());
        } else {
            System.out.println("No se encontró un artículo con ese ID.");
        }
    }

    private void ingresarStockArticulo() {
        //acá iría la verificación de entrada consola que sea un número válido para ID
        int id = leerEnteroNoNegativo("Ingrese el ID del artículo al que desea ingresar stock: ");

        //acá iría la verificación de entrada consola que sea un número válido para stock
        int cantidad = leerEnteroPositivo("Ingrese la cantidad de stock a ingresar: ");

        try {
            articuloService.ingresarStockArticulo(id, cantidad);
            System.out.println("Stock ingresado exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarArticulo() {
        //acá iría la verificación de entrada consola que sea un número válido para ID
        int id = leerEnteroNoNegativo("Ingrese el ID del artículo a eliminar: ");

        boolean exito = articuloService.eliminarArticulo(id);
        if (exito) {
            System.out.println("Artículo eliminado exitosamente.");
        } else {
            System.out.println("No se encontró un artículo con ese ID.");
        }
    }

    private void modificarDetalleEspecifico() {
        int id = leerEnteroPositivo("Ingrese el ID del artículo: ");
        Articulo articulo = articuloService.buscarArticuloPorId(id);
        if (articulo == null) {
            return;
        }

        String descripcion = articulo.getTipoArticulo().equals("Electronico")
                ? "los meses de garantía"
                : "los días para vencimiento";
        int nuevoDetalle = leerEnteroNoNegativo("Ingrese " + descripcion + ": ");
        articuloService.actualizarDetalleEspecifico(id, nuevoDetalle);
        System.out.println("Detalle actualizado exitosamente.");
    }



}
