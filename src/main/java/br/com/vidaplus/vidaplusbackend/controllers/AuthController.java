package br.com.vidaplus.vidaplusbackend.controllers;

import br.com.vidaplus.vidaplusbackend.dto.*;
import br.com.vidaplus.vidaplusbackend.entities.Role;
import br.com.vidaplus.vidaplusbackend.entities.User;
import br.com.vidaplus.vidaplusbackend.security.JwtUtils;
import br.com.vidaplus.vidaplusbackend.services.PacienteService;
import br.com.vidaplus.vidaplusbackend.services.RoleService;
import br.com.vidaplus.vidaplusbackend.services.UserService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final PacienteService pacienteService;
    private final JwtUtils jwtUtils;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserService userService,
            PacienteService pacienteService,
            JwtUtils jwtUtils
    ) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.pacienteService = pacienteService;
        this.jwtUtils = jwtUtils;
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequest) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            User user = userService.getUserByUsername(loginRequest.getUsername());

            if (!Boolean.TRUE.equals(user.getActive())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body(Map.of("error", "Usuário está desativado"));
            }

            String jwt = jwtUtils.generateJwtToken(user.getUsername());

            Set<String> roles = user.getRoles()
                    .stream()
                    .map(Role::getName)
                    .collect(Collectors.toSet());

            return ResponseEntity.ok(
                    new LoginResponseDTO(jwt, "Bearer", user.getUsername(), roles)
            );

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Username ou senha inválidos"));
        }
    }


    // REGISTRAR PACIENTE

    @PostMapping("/register/paciente")
    public ResponseEntity<?> registerPaciente(
            @Valid @RequestBody PacienteCadastroDTO dto
    ) {

        User user = pacienteService.registrarPaciente(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of(
                        "message", "Paciente registrado com sucesso",
                        "username", user.getUsername()
                ));
    }

    // VALIDAR TOKEN
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken() {
        return ResponseEntity.ok(Map.of("valid", true));
    }

    // REFRESH TOKEN
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @RequestHeader(value = "Authorization", required = false)
            String authHeader
    ) {

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Token inválido"));
        }

        String token = authHeader.substring(7);

        if (!jwtUtils.validateJwtToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Token expirado ou inválido"));
        }

        String username = jwtUtils.getUsernameFromJwtToken(token);
        String newToken = jwtUtils.generateJwtToken(username);

        return ResponseEntity.ok(Map.of(
                "token", newToken,
                "type", "Bearer"
        ));
    }

    // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(
                Map.of("message", "Logout realizado com sucesso")
        );
    }
}
