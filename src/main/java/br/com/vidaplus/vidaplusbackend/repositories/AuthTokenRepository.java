package br.com.vidaplus.vidaplusbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.vidaplus.vidaplusbackend.entities.AuthToken;

public interface AuthTokenRepository extends JpaRepository<AuthToken, Long> {
}
