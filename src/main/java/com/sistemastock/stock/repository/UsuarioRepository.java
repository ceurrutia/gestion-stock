package com.sistemastock.stock.repository;

import com.sistemastock.stock.entity.Usuario;
import com.sistemastock.stock.enums.RolUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    //busca por email
    Optional<Usuario> findByEmail(String email);

    //lista usuarios activos
    List<Usuario> findByActivoTrue();

    //lista usuarios inactivos
    List<Usuario> findByActivoFalse();

    //lista usuarios por rol
    List<Usuario> findByRol(RolUsuario rol);

    // Lista usuarios activos por rol
    List<Usuario> findByRolAndActivoTrue(RolUsuario rol);
}
