package com.sistemastock.stock.controller;

import com.sistemastock.stock.dto.UsuarioRequestDTO;
import com.sistemastock.stock.dto.UsuarioResponseDTO;
import com.sistemastock.stock.entity.Usuario;
import com.sistemastock.stock.enums.RolUsuario;
import com.sistemastock.stock.mapper.UsuarioMapper;
import com.sistemastock.stock.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // ===================== Listados =====================

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") //solo para ADMIN
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> lista = usuarioService.listarTodos()
                .stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // GET /api/usuarios/activos -> Listar activos (Requiere ADMIN)
    @GetMapping("/activos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> listarActivos() {
        List<UsuarioResponseDTO> lista = usuarioService.listarActivos()
                .stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // GET /api/usuarios/inactivos -> Listar inactivos (Requiere ADMIN)
    @GetMapping("/inactivos")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> listarInactivos() {
        List<UsuarioResponseDTO> lista = usuarioService.listarInactivos()
                .stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    // GET /api/usuarios/rol/{rol} -> Listar por rol (Requiere ADMIN)
    @GetMapping("/rol/{rol}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UsuarioResponseDTO>> listarPorRol(@PathVariable RolUsuario rol) {
        List<UsuarioResponseDTO> lista = usuarioService.listarPorRol(rol)
                .stream()
                .map(UsuarioMapper::toResponseDTO)
                .toList();
        return ResponseEntity.ok(lista);
    }

    //metodos
    // GET /api/usuarios/{id} -> Obtener por ID (Requiere ADMIN o el propio usuario)
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(UsuarioMapper.toResponseDTO(usuario));
    }

    // PUT /api/usuarios/{id} -> Actualizar usuario (Requiere ADMIN o el propio usuario)
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioRequestDTO requestDTO
    ) {
        Usuario datos = UsuarioMapper.toEntity(requestDTO);
        Usuario actualizado = usuarioService.actualizarUsuario(id, datos);
        return ResponseEntity.ok(UsuarioMapper.toResponseDTO(actualizado));
    }

    // PATCH /api/usuarios/desactivar/{id} -> Desactivar (Requiere ADMIN)
    @PatchMapping("/desactivar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> desactivarUsuario(@PathVariable Long id) {
        usuarioService.desactivarUsuario(id);
        return ResponseEntity.noContent().build();
    }

    // PATCH /api/usuarios/activar/{id} -> Activar (Requiere ADMIN)
    @PatchMapping("/activar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> activarUsuario(@PathVariable Long id) {
        usuarioService.activarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}