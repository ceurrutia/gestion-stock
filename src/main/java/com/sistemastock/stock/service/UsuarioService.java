package com.sistemastock.stock.service;

import com.sistemastock.stock.entity.Usuario;
import com.sistemastock.stock.enums.RolUsuario;
import com.sistemastock.stock.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Crear usuario
    public Usuario crearUsuario(Usuario usuario) {

        //forzar rol USUARIO si es registro público
        if (usuario.getRol() == null) {
            usuario.setRol(RolUsuario.USUARIO);
        }

        return usuarioRepository.save(usuario);
    }

    // Obtener por ID
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    public Usuario actualizarUsuario(Long id, Usuario datosActualizados) {
        Usuario usuario = obtenerPorId(id);

        usuario.setNombre(datosActualizados.getNombre());
        usuario.setApellido(datosActualizados.getApellido());
        usuario.setDni(datosActualizados.getDni());
        usuario.setDireccion(datosActualizados.getDireccion());
        usuario.setTelefono(datosActualizados.getTelefono());
        usuario.setEmail(datosActualizados.getEmail());
        usuario.setRol(datosActualizados.getRol());

        // Si te llega un password nuevo, haz hash
        if (datosActualizados.getPassword() != null && !datosActualizados.getPassword().isBlank()) {
            usuario.setPassword(passwordEncoder.encode(datosActualizados.getPassword()));
        }

        return usuarioRepository.save(usuario);
    }


    // Desactivar usuario (soft delete)
    public void desactivarUsuario(Long id) {
        Usuario usuario = obtenerPorId(id);
        usuario.desactivar();
        usuarioRepository.save(usuario);
    }

    // Activar usuario
    public void activarUsuario(Long id) {
        Usuario usuario = obtenerPorId(id);
        usuario.activar();
        usuarioRepository.save(usuario);
    }

    // Listar todos
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    // Listar activos
    public List<Usuario> listarActivos() {
        return usuarioRepository.findByActivoTrue();
    }

    // Listar inactivos
    public List<Usuario> listarInactivos() {
        return usuarioRepository.findByActivoFalse();
    }

    // Listar por rol
    public List<Usuario> listarPorRol(RolUsuario rol) {
        return usuarioRepository.findByRol(rol);
    }

    // Listar activos por rol
    public List<Usuario> listarActivosPorRol(RolUsuario rol) {
        return usuarioRepository.findByRolAndActivoTrue(rol);
    }
}
