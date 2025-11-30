package com.sistemastock.stock.dto;

import com.sistemastock.stock.enums.CategoriaProducto;

import java.time.LocalDateTime;

public class ProductoResponseDTO {

    private Long id;
    private String nombre;
    private String marca;
    private String descripcion;
    private String codigo_producto;
    private Double precioUnitario;
    private Integer stock;
    private CategoriaProducto categoria;

    private boolean activo;
    private LocalDateTime fechaAlta;
    private LocalDateTime fechaBaja;

    public ProductoResponseDTO() {}

    public ProductoResponseDTO(Long id, String nombre, String marca, String descripcion, String codigo_producto,
                               Double precioUnitario, Integer stock, CategoriaProducto categoria,
                               boolean activo, LocalDateTime fechaAlta, LocalDateTime fechaBaja) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.codigo_producto = codigo_producto;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.categoria = categoria;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaBaja = fechaBaja;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public CategoriaProducto getCategoria() { return categoria; }
    public void setCategoria(CategoriaProducto categoria) { this.categoria = categoria; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public LocalDateTime getFechaAlta() { return fechaAlta; }
    public void setFechaAlta(LocalDateTime fechaAlta) { this.fechaAlta = fechaAlta; }

    public LocalDateTime getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(LocalDateTime fechaBaja) { this.fechaBaja = fechaBaja; }
}
