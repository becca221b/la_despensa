package com.techlab.infrastructure.persistence;

import com.techlab.domain.model.Identificable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Repositorio<T extends Identificable> {
    private final List<T> elementos = new ArrayList<>();

    public boolean agregar(T elemento) {
        if (elemento == null || buscarPorCodigo(elemento.getCodigo()).isPresent()) {
            return false;
        }
        return elementos.add(elemento);
    }

    public List<T> listar() {
        return new ArrayList<>(elementos);
    }

    public Optional<T> buscarPorCodigo(int codigo) {
        return elementos.stream()
                .filter(elemento -> elemento.getCodigo() == codigo)
                .findFirst();
    }

    public boolean actualizar(T elemento) {
        if (elemento == null) {
            return false;
        }
        for (int indice = 0; indice < elementos.size(); indice++) {
            if (elementos.get(indice).getCodigo() == elemento.getCodigo()) {
                elementos.set(indice, elemento);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarPorCodigo(int codigo) {
        return elementos.removeIf(elemento -> elemento.getCodigo() == codigo);
    }
}