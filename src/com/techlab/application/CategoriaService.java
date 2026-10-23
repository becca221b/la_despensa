package com.techlab.application;

import com.techlab.domain.model.Articulo;
import com.techlab.domain.model.Categoria;
import com.techlab.domain.repository.ArticuloRepository;
import com.techlab.domain.repository.CategoriaRepository;
import com.techlab.utils.Validaciones;

import java.util.List;
import java.util.Optional;

public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ArticuloRepository articuloRepository;

    public CategoriaService(CategoriaRepository categoriaRepository, ArticuloRepository articuloRepository) {
        this.categoriaRepository = categoriaRepository;
        this.articuloRepository = articuloRepository;
    }

    public boolean agregarCategoria(String nombre, String descripcion) {
        validarDatos(nombre);
        validarNombreDisponible(nombre, null);
        int codigo = Secuencias.generarCodigoCategoria(categoriaRepository.obtenerTodasLasCategorias());
        return categoriaRepository.guardarCategoria(new Categoria(codigo, nombre.trim(), descripcion));
    }

    public List<Categoria> listarTodasLasCategorias() {
        return categoriaRepository.obtenerTodasLasCategorias();
    }

    public Optional<Categoria> buscarCategoriaPorCodigo(int codigo) {
        return categoriaRepository.obtenerCategoriaPorCodigo(codigo);
    }

    public void actualizarCategoria(int codigo, String nombre, String descripcion) {
        validarDatos(nombre);
        Categoria categoria = categoriaRepository.obtenerCategoriaPorCodigo(codigo)
                .orElseThrow(() -> new IllegalArgumentException("No se encontró una categoría con ese código."));
        validarNombreDisponible(nombre, categoria);
        categoria.setNombre(nombre.trim());
        categoria.setDescripcion(descripcion);
        categoriaRepository.actualizarCategoria(categoria);
    }

    public boolean eliminarCategoria(int codigo) {
        if (categoriaRepository.obtenerCategoriaPorCodigo(codigo).isEmpty()) {
            return false;
        }
        boolean tieneArticulosAsociados = articuloRepository.obtenerTodosLosArticulos().stream()
                .map(Articulo::getCategoria)
                .anyMatch(categoria -> categoria != null && categoria.getCodigo() == codigo);
        if (tieneArticulosAsociados) {
            throw new IllegalArgumentException("No se puede eliminar una categoría asociada a artículos.");
        }
        return categoriaRepository.eliminarCategoria(codigo);
    }

    private void validarDatos(String nombre) {
        if (!Validaciones.validarTextoNoVacio(nombre)) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
    }

    private void validarNombreDisponible(String nombre, Categoria categoriaActual) {
        Optional<Categoria> categoriaConNombre = categoriaRepository.obtenerCategoriaPorNombre(nombre.trim());
        if (categoriaConNombre.isPresent()
                && (categoriaActual == null
                        || categoriaConNombre.get().getCodigo() != categoriaActual.getCodigo())) {
            throw new IllegalArgumentException("Ya existe una categoría con ese nombre.");
        }
    }
}