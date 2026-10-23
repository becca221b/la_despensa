package com.techlab.presentation;

import com.techlab.application.CategoriaService;
import com.techlab.domain.model.Categoria;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CategoriaMenu extends Menu {
    private final CategoriaService categoriaService;

    public CategoriaMenu(CategoriaService categoriaService, Scanner scanner) {
        super(scanner);
        this.categoriaService = categoriaService;
    }

    @Override
    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            System.out.println("=== Menú de Categorías ===");
            System.out.println("1. Agregar categoría");
            System.out.println("2. Listar categorías");
            System.out.println("3. Buscar categoría por código");
            System.out.println("4. Modificar categoría");
            System.out.println("5. Eliminar categoría");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = Integer.parseInt(scanner.nextLine());
            switch (opcion) {
                case 1:
                    agregarCategoria();
                    break;
                case 2:
                    listarCategorias();
                    break;
                case 3:
                    buscarCategoria();
                    break;
                case 4:
                    modificarCategoria();
                    break;
                case 5:
                    eliminarCategoria();
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void agregarCategoria() {
        String nombre = leerTextoNoVacio("Ingrese el nombre: ");
        String descripcion = leerTextoNoVacio("Ingrese la descripción: ");
        try {
            categoriaService.agregarCategoria(nombre, descripcion);
            System.out.println("Categoría agregada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void listarCategorias() {
        List<Categoria> categorias = categoriaService.listarTodasLasCategorias();
        if (categorias.isEmpty()) {
            System.out.println("No hay categorías registradas.");
            return;
        }
        categorias.forEach(System.out::println);
    }

    private void buscarCategoria() {
        int codigo = leerEnteroPositivo("Ingrese el código de la categoría: ");
        Optional<Categoria> categoria = categoriaService.buscarCategoriaPorCodigo(codigo);
        if (categoria.isPresent()) {
            System.out.println(categoria.get());
        } else {
            System.out.println("No existe una categoría con ese código.");
        }
    }

    private void modificarCategoria() {
        int codigo = leerEnteroPositivo("Ingrese el código de la categoría: ");
        String nombre = leerTextoNoVacio("Ingrese el nuevo nombre: ");
        String descripcion = leerTextoNoVacio("Ingrese la nueva descripción: ");
        try {
            categoriaService.actualizarCategoria(codigo, nombre, descripcion);
            System.out.println("Categoría modificada exitosamente.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void eliminarCategoria() {
        int codigo = leerEnteroPositivo("Ingrese el código de la categoría: ");
        try {
            if (categoriaService.eliminarCategoria(codigo)) {
                System.out.println("Categoría eliminada exitosamente.");
            } else {
                System.out.println("No existe una categoría con ese código.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}