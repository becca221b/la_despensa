package com.techlab.infrastructure.persistence;

import com.techlab.domain.model.Categoria;
import com.techlab.domain.repository.CategoriaRepository;

import java.util.List;
import java.util.Optional;

public class CategoriaRepositoryImpl implements CategoriaRepository {
    private final Repositorio<Categoria> repositorio = new Repositorio<>();

    @Override
    public boolean guardarCategoria(Categoria categoria) {
        return repositorio.agregar(categoria);
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorCodigo(int codigo) {
        return repositorio.buscarPorCodigo(codigo);
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorNombre(String nombre) {
        return repositorio.listar().stream()
                .filter(categoria -> categoria.getNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        return repositorio.listar();
    }

    @Override
    public boolean actualizarCategoria(Categoria categoria) {
        return repositorio.actualizar(categoria);
    }

    @Override
    public boolean eliminarCategoria(int codigo) {
        return repositorio.eliminarPorCodigo(codigo);
    }
}