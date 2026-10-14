package com.techlab.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.techlab.domain.model.Orden;
import com.techlab.domain.repository.OrdenRepository;

public class OrdenRepositoryImpl implements OrdenRepository {

    private final List<Orden> ordenes = new ArrayList<>();
    // Implementación de los métodos del repositorio de órdenes
    @Override
    public boolean guardarOrden(Orden orden) {
        // Implementación para guardar la orden en la base de datos
        ordenes.add(orden);
        return true;
    }

    @Override
    public Optional<Orden> obtenerOrdenPorId(int id) {
        // Implementación para obtener una orden por su ID desde la base de datos
        return ordenes.stream()
                .filter(orden -> orden.getId() == id)
                .findFirst();
    }

    @Override
    public List<Orden> obtenerTodasLasOrdenes() {
        // Implementación para obtener todas las órdenes desde la base de datos
        return ordenes;
    }

    @Override
    public boolean actualizarOrden(Orden orden) {
        // Implementación para actualizar una orden en la base de datos
        Optional<Orden> ordenExistente = obtenerOrdenPorId(orden.getId());
        if (ordenExistente.isPresent()) {
            ordenes.remove(ordenExistente.get());
            ordenes.add(orden);
            return true;
        }
        return false;
    }
    

    @Override
    public boolean eliminarOrden(int id) {
        // Implementación para eliminar una orden por su ID desde la base de datos
        Optional<Orden> ordenExistente = obtenerOrdenPorId(id);
        if (ordenExistente.isPresent()) {
            ordenes.remove(ordenExistente.get());
            return true;
        }
        System.out.println("No se encontró una orden con el ID proporcionado.");
        return false;
    }

    

}
