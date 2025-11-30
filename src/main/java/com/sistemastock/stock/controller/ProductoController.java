package com.sistemastock.stock.controller;

import com.sistemastock.stock.dto.ProductoRequestDTO;
import com.sistemastock.stock.dto.ProductoResponseDTO;
import com.sistemastock.stock.entity.Producto;
import com.sistemastock.stock.mapper.ProductoMapper;
import com.sistemastock.stock.service.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // Crear producto
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(
            @RequestBody ProductoRequestDTO requestDTO
    ) {
        Producto producto = ProductoMapper.toEntity(requestDTO);
        Producto guardado = productoService.crearProducto(producto);

        return ResponseEntity.ok(ProductoMapper.toResponseDTO(guardado));
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<ProductoResponseDTO> lista = productoService.obtenerTodos()
                .stream()
                .map(ProductoMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(ProductoMapper.toResponseDTO(producto));
    }

    // Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(
            @PathVariable Long id,
            @RequestBody ProductoRequestDTO requestDTO
    ) {
        Producto datos = ProductoMapper.toEntity(requestDTO);
        Producto actualizado = productoService.actualizarProducto(id, datos);

        return ResponseEntity.ok(ProductoMapper.toResponseDTO(actualizado));
    }

    // Desactivar (soft delete)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar por nombre
    @GetMapping("/buscar/nombre")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorNombre(
            @RequestParam String nombre
    ) {
        List<ProductoResponseDTO> lista = productoService.buscarPorNombre(nombre)
                .stream()
                .map(ProductoMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // Buscar por marca
    @GetMapping("/buscar/marca")
    public ResponseEntity<List<ProductoResponseDTO>> buscarPorMarca(
            @RequestParam String marca
    ) {
        List<ProductoResponseDTO> lista = productoService.buscarPorMarca(marca)
                .stream()
                .map(ProductoMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // Listar activos
    @GetMapping("/activos")
    public ResponseEntity<List<ProductoResponseDTO>> listarActivos() {
        List<ProductoResponseDTO> lista = productoService.listarActivos()
                .stream()
                .map(ProductoMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }

    // Listar inactivos
    @GetMapping("/inactivos")
    public ResponseEntity<List<ProductoResponseDTO>> listarInactivos() {
        List<ProductoResponseDTO> lista = productoService.listarInactivos()
                .stream()
                .map(ProductoMapper::toResponseDTO)
                .toList();

        return ResponseEntity.ok(lista);
    }
}
