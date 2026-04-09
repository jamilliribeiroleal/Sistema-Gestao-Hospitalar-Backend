package br.com.vidaplus.vidaplusbackend.services;

import br.com.vidaplus.vidaplusbackend.entities.Role;
import br.com.vidaplus.vidaplusbackend.entities.User;
import br.com.vidaplus.vidaplusbackend.exceptions.ResourceNotFoundException;
import br.com.vidaplus.vidaplusbackend.repositories.RoleRepository;
import br.com.vidaplus.vidaplusbackend.repositories.UserRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {

		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

//  VERIFICAÇÕES

	@Transactional(readOnly = true)
	public boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Transactional(readOnly = true)
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	// CRIAÇÃO

	public User createUser(User user, Set<String> roleNames) {

		Set<Role> roles = resolveRoles(roleNames);

		user.setRoles(roles);
		user.setActive(true);
		user.setCreatedAt(LocalDateTime.now());

		return userRepository.save(user);
	}

	public User save(User user) {
		return userRepository.save(user);
	}

// ✏️ ATUALIZAÇÃO

	public User updateUser(Long id, User updatedUser) {

		User existingUser = getUserById(id);

		existingUser.setUsername(updatedUser.getUsername());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setActive(updatedUser.getActive());

		if (updatedUser.getPasswordHash() != null && !updatedUser.getPasswordHash().isBlank()) {

			existingUser.setPasswordHash(passwordEncoder.encode(updatedUser.getPasswordHash()));
		}

		existingUser.setUpdatedAt(LocalDateTime.now());

		return userRepository.save(existingUser);
	}

	// BUSCAS

	@Transactional(readOnly = true)
	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com id: " + id));
	}

	@Transactional(readOnly = true)
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com username: " + username));
	}

	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

//  SOFT DELETE

	public void deactivateUser(Long id) {
		User user = getUserById(id);
		user.setActive(false);
		user.setDeletedAt(LocalDateTime.now());
		userRepository.save(user);
	}

	// ROLES

	public User addRolesToUser(Long userId, Set<String> roleNames) {

		User user = getUserById(userId);
		user.getRoles().addAll(resolveRoles(roleNames));

		return userRepository.save(user);
	}

	public User removeRolesFromUser(Long userId, Set<String> roleNames) {

		User user = getUserById(userId);

		roleNames.forEach(roleName -> user.getRoles().remove(findRoleByName(roleName)));

		return userRepository.save(user);
	}

	// ROLE HELPERS

	@Transactional(readOnly = true)
	public Role findRoleByName(String roleName) {
		return roleRepository.findByName(roleName)
				.orElseThrow(() -> new ResourceNotFoundException("Role não encontrada: " + roleName));
	}

	private Set<Role> resolveRoles(Set<String> roleNames) {

		Set<Role> roles = new HashSet<>();

		for (String roleName : roleNames) {
			roles.add(findRoleByName(roleName));
		}

		return roles;
	}
}
