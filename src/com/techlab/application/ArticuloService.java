package com.techlab.application;

import java.util.Optional;
import java.util.List;

import com.techlab.domain.model.Articulo;
import com.techlab.domain.model.ArticuloAlimenticio;
import com.techlab.domain.model.ArticuloElectronico;
import com.techlab.domain.model.Categoria;
import com.techlab.domain.repository.ArticuloRepository;

public class ArticuloService {
 private final ArticuloRepository articuloRepository;

    public ArticuloService(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    public boolean agregarArticuloElectronico(String nombre, double precio, int cantidadStock,
            Categoria categoria, int garantiaMeses) {
        Articulo articulo = new ArticuloElectronico(
            Secuencias.generarCodigoArticulo(articuloRepository.obtenerTodosLosArticulos()),
            nombre, precio, categoria, cantidadStock, garantiaMeses);
        return agregarArticulo(articulo);
    }

    public boolean agregarArticuloAlimenticio(String nombre, double precio, int cantidadStock,
            Categoria categoria, int diasParaVencimiento) {
        Articulo articulo = new ArticuloAlimenticio(
            Secuencias.generarCodigoArticulo(articuloRepository.obtenerTodosLosArticulos()),
            nombre, precio, categoria, cantidadStock, diasParaVencimiento);
        return agregarArticulo(articulo);
    }

    private boolean agregarArticulo(Articulo articulo) {
        if (articuloRepository.obtenerArticuloPorNombre(articulo.getNombre()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un artículo con el mismo nombre.");
        }
        return articuloRepository.guardarArticulo(articulo);
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
            articuloRepository.actualizarArticulo(articulo);
        } else {
            throw new IllegalArgumentException("No se encontró un artículo con el ID proporcionado.");
        }
    }

    public List<Articulo> listarTodosLosArticulos() {
        return articuloRepository.obtenerTodosLosArticulos();
    }

    public void actualizarDetalleEspecifico(int id, int nuevoDetalle) {
        Articulo articulo = articuloRepository.obtenerArticuloPorId(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró un artículo con el ID proporcionado."));

        if (articulo instanceof ArticuloElectronico) {
            ((ArticuloElectronico) articulo).setGarantiaMeses(nuevoDetalle);
        } else if (articulo instanceof ArticuloAlimenticio) {
            ((ArticuloAlimenticio) articulo).setDiasParaVencimiento(nuevoDetalle);
        }
        articuloRepository.actualizarArticulo(articulo);
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

}
