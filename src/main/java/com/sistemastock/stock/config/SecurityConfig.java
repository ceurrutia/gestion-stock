package com.sistemastock.stock.config;

import com.sistemastock.stock.service.CustomUserDetailsService;
import com.sistemastock.stock.util.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter,
                          CustomUserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. RUTAS PÚBLICAS
                        .requestMatchers("/api/auth/**").permitAll()

                        // 2. RESTRICCIONES DE LECTURA ESPECÍFICAS (GET /activos, /inactivos) - ADMIN/GESTOR
                        .requestMatchers(HttpMethod.GET, "/api/productos/activos").hasAnyRole("ADMIN", "GESTOR")
                        .requestMatchers(HttpMethod.GET, "/api/productos/inactivos").hasAnyRole("ADMIN", "GESTOR")

                        // 3. RESTRICCIONES DE MODIFICACIÓN (POST, PUT, DELETE) - ADMIN/GESTOR
                        .requestMatchers(HttpMethod.POST, "/api/productos/**").hasAnyRole("ADMIN", "GESTOR")
                        .requestMatchers(HttpMethod.PUT, "/api/productos/**").hasAnyRole("ADMIN", "GESTOR")
                        .requestMatchers(HttpMethod.DELETE, "/api/productos/**").hasAnyRole("ADMIN", "GESTOR")

                        // 4. ACCESO DE LECTURA GENERAL (GET /productos/ o /productos/{id}) - LIBRE
                        .requestMatchers(HttpMethod.GET, "/api/productos/**").permitAll()

                        // 5. GESTIÓN DE USUARIOS - SOLO ADMIN
                        .requestMatchers("/api/usuarios/**").hasRole("ADMIN")

                        //si no, auth
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .userDetailsService(userDetailsService)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}


