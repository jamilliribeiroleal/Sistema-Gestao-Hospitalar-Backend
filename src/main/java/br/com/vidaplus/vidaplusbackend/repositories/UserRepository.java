package br.com.vidaplus.vidaplusbackend.repositories;

import br.com.vidaplus.vidaplusbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Busca por username (login)
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    // Mantém email para validação de unicidade
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
