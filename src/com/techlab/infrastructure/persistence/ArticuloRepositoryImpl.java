package com.techlab.infrastructure.persistence;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import com.techlab.domain.model.Articulo;
import com.techlab.domain.repository.ArticuloRepository;

public class ArticuloRepositoryImpl implements ArticuloRepository {
    
    private final List<Articulo> articulos = new ArrayList<>();

    @Override
    public boolean guardarArticulo(Articulo articulo) {
        // Implementación para guardar un artículo en la base de datos
        articulos.add(articulo);
        return true;
    }

    @Override
    public Optional<Articulo> obtenerArticuloPorNombre(String nombre) {
        // Implementación para obtener un artículo por su nombre desde la base de datos
        return articulos.stream()
                .filter(articulo -> articulo.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    @Override
    public Optional<Articulo> obtenerArticuloPorId(int id) {
        // Implementación para obtener un artículo por su ID desde la base de datos
        return articulos.stream()
                .filter(articulo -> articulo.getId() == id)
                .findFirst();
    }

    @Override
    public List<Articulo> obtenerTodosLosArticulos() {
        // Implementación para obtener todos los artículos desde la base de datos
        return articulos;
    }

    @Override
    public boolean actualizarArticulo(Articulo articulo) {
        // Implementación para actualizar un artículo en la base de datos
        Optional<Articulo> articuloExistente = obtenerArticuloPorId(articulo.getId());
        if (articuloExistente.isPresent()) {
            articulos.remove(articuloExistente.get());
            articulos.add(articulo);
            return true;
        }
        return false;
    }

    @Override
    public boolean eliminarArticulo(int id) {
        // Implementación para eliminar un artículo por su ID desde la base de datos
        Optional<Articulo> articuloExistente = obtenerArticuloPorId(id);
        if (articuloExistente.isPresent()) {
            articulos.remove(articuloExistente.get());
            return true;
        }
        System.out.println("No se encontró un artículo con el ID proporcionado.");
        return false;
    }

}
