package com.techlab.application;

import java.util.Optional;
import java.util.List;

import com.techlab.domain.model.Articulo;
import com.techlab.domain.repository.ArticuloRepository;
//import com.techlab.exceptions.StockInsuficienteException;

public class ArticuloService {
 private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public void agregarArticulo(String nombre, double precio, int cantidadStock) {
        Articulo articulo = new Articulo(nombre, precio, cantidadStock);
        if (articuloRepository.obtenerArticuloPorNombre(nombre).isPresent()) {
            throw new IllegalArgumentException("Ya existe un artículo con el mismo nombre.");
        }
        articuloRepository.guardarArticulo(articulo);
    }

    public void buscarArticuloPorNombre(String nombre) {
        Optional<Articulo> articuloOpt = articuloRepository.obtenerArticuloPorNombre(nombre);
        if (articuloOpt.isPresent()) {
            Articulo articulo = articuloOpt.get();
            System.out.println("Artículo encontrado: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio() + ", Stock: " + articulo.getCantidadStock());
        } else {
            System.out.println("No se encontró un artículo con el nombre proporcionado.");
        }
    }

    public void buscarArticuloPorId(int id) {
        Optional<Articulo> articuloOpt = articuloRepository.obtenerArticuloPorId(id);
        if (articuloOpt.isPresent()) {
            Articulo articulo = articuloOpt.get();
            System.out.println("Artículo encontrado: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio() + ", Stock: " + articulo.getCantidadStock());
        } else {
            System.out.println("No se encontró un artículo con el ID proporcionado.");
        }
    }

    public void ingresarStockArticulo(int id, int cantidad) {
        Optional<Articulo> articuloOpt = articuloRepository.obtenerArticuloPorId(id);
        if (articuloOpt.isPresent()) {
            Articulo articulo = articuloOpt.get();
            articulo.ingresarStock(cantidad);
            articuloRepository.guardarArticulo(articulo);
        } else {
            throw new IllegalArgumentException("No se encontró un artículo con el ID proporcionado.");
        }
    }

    public void actualizarPrecioArticulo(int id, double nuevoPrecio) {
        Optional<Articulo> articuloOpt = articuloRepository.obtenerArticuloPorId(id);
        if (articuloOpt.isPresent()) {
            Articulo articulo = articuloOpt.get();
            articulo.cambiarPrecio(nuevoPrecio);
            articuloRepository.guardarArticulo(articulo);
        } else {
            throw new IllegalArgumentException("No se encontró un artículo con el ID proporcionado.");
        }
    }

    public List<Articulo> listarTodosLosArticulos() {
        return articuloRepository.obtenerTodosLosArticulos();
    }


}
