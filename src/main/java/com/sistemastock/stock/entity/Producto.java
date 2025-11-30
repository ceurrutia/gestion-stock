package com.sistemastock.stock.entity;

import com.sistemastock.stock.enums.CategoriaProducto;
import jakarta.persistence.*;


@Entity
@Table(name = "productos")
public class Producto extends BaseEntity{

    @Column(nullable = false, length = 150)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String marca;

    @Column(length = 500)
    private String descripcion;

    @Column(length = 500)
    private String codigo_producto;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CategoriaProducto categoria;

    //constructor vacio
    public Producto() {
    }

    //constructor con parametros

    public Producto(String nombre, String descripcion, String codigo_producto, Double precioUnitario,
                    Integer stock, CategoriaProducto categoria) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.codigo_producto = codigo_producto;
        this.precioUnitario = precioUnitario;
        this.stock = stock;
        this.categoria = categoria;
    }

    //Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCodigo_producto() {
        return codigo_producto;
    }

    public void setCodigo_producto(String codigo_producto) {
        this.codigo_producto = codigo_producto;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public CategoriaProducto getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaProducto categoria) {
        this.categoria = categoria;
    }
}
