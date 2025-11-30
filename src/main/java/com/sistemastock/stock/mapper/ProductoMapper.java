package com.sistemastock.stock.mapper;

import com.sistemastock.stock.dto.ProductoResponseDTO;
import com.sistemastock.stock.dto.ProductoRequestDTO;
import com.sistemastock.stock.entity.Producto;

public class ProductoMapper {

    private ProductoMapper() {}

    public static Producto toEntity(ProductoRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Producto producto = new Producto();
        producto.setNombre(dto.getNombre());
        producto.setMarca(dto.getMarca());
        producto.setDescripcion(dto.getDescripcion());
        producto.setCodigo_producto(dto.getCodigo_producto());
        producto.setPrecioUnitario(dto.getPrecioUnitario());
        producto.setStock(dto.getStock());
        producto.setCategoria(dto.getCategoria());

        //campos de BaseEntity se generan automáticamente
        return producto;
    }

    //de Entity -> ResponseDTO
    public static ProductoResponseDTO toResponseDTO(Producto producto) {
        if (producto == null) {
            return null;
        }

        ProductoResponseDTO dto = new ProductoResponseDTO();

        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setMarca(producto.getMarca());
        dto.setDescripcion(producto.getDescripcion());
        dto.setCodigo_producto(producto.getCodigo_producto());
        dto.setPrecioUnitario(producto.getPrecioUnitario());
        dto.setStock(producto.getStock());
        dto.setCategoria(producto.getCategoria());

        //campitos de auditoría heredados de la BaseEntity
        dto.setActivo(producto.isActivo());
        dto.setFechaAlta(producto.getFechaAlta());
        dto.setFechaBaja(producto.getFechaBaja());

        return dto;
    }

}
