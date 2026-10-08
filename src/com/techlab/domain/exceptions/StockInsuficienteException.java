package com.techlab.domain.exceptions;

public class StockInsuficienteException extends Exception {
    
    
    public StockInsuficienteException(int cantidadSolicitada, int cantidadDisponible) {
        super("No hay suficiente stock. Solicitado: " + cantidadSolicitada + ", Disponible: " + cantidadDisponible);
    }

    
}
