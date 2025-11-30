package com.sistemastock.stock.service;

import com.sistemastock.stock.entity.Producto;
import com.sistemastock.stock.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    //Inyecto por constructor
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Crear producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    //obtener todos
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    //obtener por ID
    public Producto obtenerPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    //actualizar
    public Producto actualizarProducto(Long id, Producto datosActualizados) {
        Producto producto = obtenerPorId(id);

        producto.setNombre(datosActualizados.getNombre());
        producto.setDescripcion(datosActualizados.getDescripcion());
        producto.setCodigo_producto(datosActualizados.getCodigo_producto());
        producto.setPrecioUnitario(datosActualizados.getPrecioUnitario());
        producto.setStock(datosActualizados.getStock());
        producto.setMarca(datosActualizados.getMarca());
        producto.setCategoria(datosActualizados.getCategoria());
        producto.setActivo(datosActualizados.isActivo());

        return productoRepository.save(producto);
    }

    // desactiva producto
    public void eliminarProducto(Long id) {
        Producto producto = obtenerPorId(id);
        producto.desactivar();
        productoRepository.save(producto);
    }

    // Buscar por nombre
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Buscar por marca
    public List<Producto> buscarPorMarca(String marca) {
        return productoRepository.findByMarcaContainingIgnoreCase(marca);
    }

    // Listar activos
    public List<Producto> listarActivos() {
        return productoRepository.findByActivoTrue();
    }

    // Listar inactivos
    public List<Producto> listarInactivos() {
        return productoRepository.findByActivoFalse();
    }
}
