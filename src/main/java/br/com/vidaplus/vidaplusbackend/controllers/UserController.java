package br.com.vidaplus.vidaplusbackend.controllers;

import br.com.vidaplus.vidaplusbackend.dto.UserMeResponseDTO;
import br.com.vidaplus.vidaplusbackend.entities.User;
import br.com.vidaplus.vidaplusbackend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

 //  USUÁRIO LOGADO (JWT)
 @GetMapping("/me")
    public ResponseEntity<UserMeResponseDTO> me(Authentication authentication) {

 
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);

        Set<String> roles = user.getRoles()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toSet());

        UserMeResponseDTO response = new UserMeResponseDTO(
                user.getId(),
                user.getUsername(),  
                user.getEmail(),
                roles
        );

        return ResponseEntity.ok(response);
    }
}
