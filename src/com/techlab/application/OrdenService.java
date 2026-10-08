package com.techlab.application;

import com.techlab.domain.model.Orden;
import com.techlab.domain.model.Articulo;
import com.techlab.domain.model.Pedido;
import com.techlab.domain.repository.OrdenRepository;
import com.techlab.domain.repository.ArticuloRepository;
import com.techlab.domain.exceptions.StockInsuficienteException;




public class OrdenService {
    private final OrdenRepository ordenRepository;
    private final ArticuloRepository articuloRepository;

    public OrdenService(OrdenRepository ordenRepository, ArticuloRepository articuloRepository) {
        this.ordenRepository = ordenRepository;
        this.articuloRepository = articuloRepository;
    }

    public Orden crearOrden(int idCliente) {
        Orden orden = new Orden(generarId());
        ordenRepository.guardarOrden(orden);
        return orden;
    }

    public Orden obtenerOrdenPorId(int id) {
        return ordenRepository.obtenerOrdenPorId(id)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una orden con el ID proporcionado."));
    }

    public void agregarArticuloAOrden(
        int idOrden,
        int idArticulo,
        int cantidad) {
        Orden orden = obtenerOrdenPorId(idOrden);

        Articulo articulo = articuloRepository.obtenerArticuloPorId(idArticulo)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró un artículo con el ID proporcionado."));
        
        
        try {
            orden.agregarPedido(new Pedido(articulo, cantidad));
            articulo.restarStock(cantidad);
        } catch (StockInsuficienteException stockException) {
            throw new IllegalArgumentException(stockException.getMessage(), stockException);
        }
        
        ordenRepository.guardarOrden(orden);
    }

    private int generarId() {
        return ordenRepository.obtenerTodasLasOrdenes().size() + 1;
    }
}
