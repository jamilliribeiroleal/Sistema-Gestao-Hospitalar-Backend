package br.com.vidaplus.vidaplusbackend.services;

import br.com.vidaplus.vidaplusbackend.entities.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> findAll();

    Optional<Role> findById(Long id);

    Optional<Role> findByName(String name);

    Role save(Role role);

    void delete(Long id);

}
