package com.sistemastock.stock.dto;

import com.sistemastock.stock.enums.CategoriaProducto;
import jakarta.validation.constraints.*;

public class ProductoRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La marca es obligatoria")
    private String marca;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    @NotBlank(message = "El código de producto es obligatorio")
    private String codigo_producto;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio unitario debe ser mayor a 0")
    private Double precioUnitario;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "La categoría es obligatoria")
    private CategoriaProducto categoria;

    //constructor vacío
    public ProductoRequestDTO() {
    }

    //constructor con parámetros
    public ProductoRequestDTO(String nombre, String marca, String descripcion, String codigo_producto,
                              Double precioUnitario, Integer stock, CategoriaProducto categoria) {
        this.nombre = nombre;
        this.marca = marca;
        this.descripcion = descripcion;
        this.codigo_producto = codigo_producto;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.categoria = categoria;
    }

    //Getters y setters

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCodigo_producto() { return codigo_producto; }
    public void setCodigo_producto(String codigo_producto) { this.codigo_producto = codigo_producto; }

    public Double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(Double precioUnitario) { this.precioUnitario = precioUnitario; }

    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }

    public CategoriaProducto getCategoria() { return categoria; }
    public void setCategoria(CategoriaProducto categoria) { this.categoria = categoria; }
}
