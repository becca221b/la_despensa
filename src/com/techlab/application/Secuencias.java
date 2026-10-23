package com.techlab.application;

import java.util.List;

import com.techlab.domain.model.Articulo;
import com.techlab.domain.model.Categoria;
import com.techlab.domain.model.Identificable;

public final class Secuencias {
    private static int proximoCodigoArticulo = 1;
    private static int proximoCodigoCategoria = 1;

    private Secuencias() {
    }

    public static int generarCodigoArticulo(List<Articulo> articulos) {
        proximoCodigoArticulo = Math.max(proximoCodigoArticulo, obtenerSiguienteCodigo(articulos));
        return proximoCodigoArticulo++;
    }

    public static int generarCodigoCategoria(List<Categoria> categorias) {
        proximoCodigoCategoria = Math.max(proximoCodigoCategoria, obtenerSiguienteCodigo(categorias));
        return proximoCodigoCategoria++;
    }

    private static <T extends Identificable> int obtenerSiguienteCodigo(List<T> elementos) {
        return elementos.stream()
                .mapToInt(Identificable::getCodigo)
                .max()
                .orElse(0) + 1;
    }
}