package com.sistemastock.stock.mapper;

import com.sistemastock.stock.dto.UsuarioRequestDTO;
import com.sistemastock.stock.dto.UsuarioResponseDTO;
import com.sistemastock.stock.entity.Usuario;

public class UsuarioMapper {
    //de RequestDTO a Entity
    public static Usuario toEntity(UsuarioRequestDTO dto) {
        if (dto == null) return null;

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setDni(dto.getDni());
        usuario.setDireccion(dto.getDireccion());
        usuario.setTelefono(dto.getTelefono());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword()); //ojo que aca no encripta
        usuario.setRol(dto.getRol());

        return usuario;
    }

    //de Entidad a ResponseDTO
    public static UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        if (usuario == null) return null;

        UsuarioResponseDTO dto = new UsuarioResponseDTO();

        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setDni(usuario.getDni());
        dto.setDireccion(usuario.getDireccion());
        dto.setTelefono(usuario.getTelefono());
        dto.setEmail(usuario.getEmail());
        dto.setRol(usuario.getRol());

        // Campos de BaseEntity
        dto.setActivo(usuario.isActivo());
        dto.setFechaAlta(usuario.getFechaAlta());
        dto.setFechaBaja(usuario.getFechaBaja());

        return dto;
    }
}
