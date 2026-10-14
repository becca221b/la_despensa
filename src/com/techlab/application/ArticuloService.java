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

    public boolean agregarArticulo(String nombre, double precio, int cantidadStock) {
        Articulo articulo = new Articulo(generarId(), nombre, precio, cantidadStock);
        if (articuloRepository.obtenerArticuloPorNombre(nombre).isPresent()) {
            throw new IllegalArgumentException("Ya existe un artículo con el mismo nombre.");
        }
        articuloRepository.guardarArticulo(articulo);
        return true;
    }   
    

    public Articulo buscarArticuloPorNombre(String nombre) {
        Optional<Articulo> articuloOpt = articuloRepository.obtenerArticuloPorNombre(nombre);
        if (articuloOpt.isPresent()) {
            Articulo articulo = articuloOpt.get();
            System.out.println("Artículo encontrado: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio() + ", Stock: " + articulo.getCantidadStock());
            return articulo;
        } else {
            System.out.println("No se encontró un artículo con el nombre proporcionado.");
            return null;
        }
    }

    public Articulo buscarArticuloPorId(int id) {
        Optional<Articulo> articuloOpt = articuloRepository.obtenerArticuloPorId(id);
        if (articuloOpt.isPresent()) {
            Articulo articulo = articuloOpt.get();
            System.out.println("Artículo encontrado: " + articulo.getNombre() + ", Precio: " + articulo.getPrecio() + ", Stock: " + articulo.getCantidadStock());
            return articulo;
        } else {
            System.out.println("No se encontró un artículo con el ID proporcionado.");
            return null;
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

    public boolean eliminarArticulo(int id) {
        Optional<Articulo> articuloOpt = articuloRepository.obtenerArticuloPorId(id);
        if (articuloOpt.isPresent()) {
            articuloRepository.eliminarArticulo(id);
            return true;
        } else {
            throw new IllegalArgumentException("No se encontró un artículo con el ID proporcionado.");
        }
    }

    private int generarId() {
        return articuloRepository.obtenerTodosLosArticulos().size() + 1;
    }

}
