package com.sistemastock.stock.controller;

import com.sistemastock.stock.dto.UsuarioRequestDTO;
import com.sistemastock.stock.entity.Usuario;
import com.sistemastock.stock.enums.RolUsuario;
import com.sistemastock.stock.service.UsuarioService;
import com.sistemastock.stock.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioService usuarioService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UsuarioService usuarioService,
                          AuthenticationManager authManager,
                          JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    // ===================== Registro =====================
    @PostMapping("/registro")
    public ResponseEntity<?> registro(@RequestBody UsuarioRequestDTO requestDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(requestDTO.getNombre());
        usuario.setApellido(requestDTO.getApellido());
        usuario.setEmail(requestDTO.getEmail());
        //pass hsheado
        usuario.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        usuario.setRol(requestDTO.getRol() != null ? requestDTO.getRol() : RolUsuario.USUARIO);
        usuario.setActivo(true);

        usuario.setDireccion(requestDTO.getDireccion());
        usuario.setDni(requestDTO.getDni());
        usuario.setTelefono(requestDTO.getTelefono());

        Usuario guardado = usuarioService.crearUsuario(usuario);
        return ResponseEntity.ok(Map.of(
                "mensaje", "Usuario creado",
                "id", guardado.getId(),
                "email", guardado.getEmail()
        ));
    }

    // ===================== Login =====================
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequestDTO requestDTO) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDTO.getEmail(),
                            requestDTO.getPassword()
                    )
            );

            UserDetails user = (UserDetails) auth.getPrincipal();
            String token = jwtUtil.generateToken(user);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            System.err.println("Error de autenticación: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}