package com.techlab.domain.repository;

import com.techlab.domain.model.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {
    boolean guardarCategoria(Categoria categoria);
    Optional<Categoria> obtenerCategoriaPorCodigo(int codigo);
    Optional<Categoria> obtenerCategoriaPorNombre(String nombre);
    List<Categoria> obtenerTodasLasCategorias();
    boolean actualizarCategoria(Categoria categoria);
    boolean eliminarCategoria(int codigo);
}