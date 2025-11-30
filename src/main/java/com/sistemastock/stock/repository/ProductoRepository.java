package com.sistemastock.stock.repository;

import com.sistemastock.stock.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    //listar  activos
    List<Producto> findByActivoTrue();

    //listar inactivos
    List<Producto> findByActivoFalse();

    //buscar por nombre (útil para filtros)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    //busca por marca
    List<Producto> findByMarcaContainingIgnoreCase(String marca);
}
