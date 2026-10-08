package com.techlab.domain.repository;

import com.techlab.domain.model.Articulo;

import java.util.List;
import java.util.Optional;

public interface ArticuloRepository {
    boolean guardarArticulo(Articulo articulo);
    Optional<Articulo> obtenerArticuloPorNombre(String nombre);
    Optional<Articulo> obtenerArticuloPorId(int id);
    List <Articulo> obtenerTodosLosArticulos();
    boolean actualizarArticulo(Articulo articulo);
    boolean eliminarArticulo(int id);
}
