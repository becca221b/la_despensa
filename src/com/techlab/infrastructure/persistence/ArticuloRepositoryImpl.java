package com.techlab.infrastructure.persistence;

import java.util.Optional;
import java.util.List;

import com.techlab.domain.model.Articulo;
import com.techlab.domain.repository.ArticuloRepository;

public class ArticuloRepositoryImpl implements ArticuloRepository {
    private final Repositorio<Articulo> repositorio = new Repositorio<>();

    @Override
    public boolean guardarArticulo(Articulo articulo) {
        return repositorio.agregar(articulo);
    }

    @Override
    public Optional<Articulo> obtenerArticuloPorNombre(String nombre) {
        // Implementación para obtener un artículo por su nombre desde la base de datos
        return repositorio.listar().stream()
                .filter(articulo -> articulo.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    @Override
    public Optional<Articulo> obtenerArticuloPorId(int id) {
        // Implementación para obtener un artículo por su ID desde la base de datos
        return repositorio.buscarPorCodigo(id);
    }

    @Override
    public List<Articulo> obtenerTodosLosArticulos() {
        // Implementación para obtener todos los artículos desde la base de datos
        return repositorio.listar();
    }

    @Override
    public boolean actualizarArticulo(Articulo articulo) {
        // Implementación para actualizar un artículo en la base de datos
        return repositorio.actualizar(articulo);
    }

    @Override
    public boolean eliminarArticulo(int id) {
        // Implementación para eliminar un artículo por su ID desde la base de datos
        return repositorio.eliminarPorCodigo(id);
    }

}
