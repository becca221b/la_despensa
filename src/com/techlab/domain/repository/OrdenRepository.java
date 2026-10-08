package com.techlab.domain.repository;

import com.techlab.domain.model.Orden;
import java.util.List;
import java.util.Optional;

public interface OrdenRepository {
    boolean guardarOrden(Orden orden);
    Optional<Orden> obtenerOrdenPorId(int id);
    List<Orden> obtenerTodasLasOrdenes();
    boolean actualizarOrden(Orden orden);
    boolean eliminarOrden(int id);

}
