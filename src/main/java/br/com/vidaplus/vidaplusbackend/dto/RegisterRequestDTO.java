package br.com.vidaplus.vidaplusbackend.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class RegisterRequestDTO {

	private String username;

	@NotBlank(message = "Nome completo é obrigatório")
	private String fullName;

	@NotBlank(message = "Email é obrigatório")
	@Email(message = "Email inválido")
	private String email;

	@NotBlank(message = "Senha é obrigatória")
	private String password;

	@NotBlank(message = "Role é obrigatória")
	private String role;

	@NotBlank(message = "Sexo é obrigatório")
	private String sexo;

	@NotNull(message = "Campo 'dataNascimento' é obrigatório")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	private String cpf;
	private String telefone;
	private String endereco;

	public RegisterRequestDTO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	// MAPPER PARA DTO DE SERVIÇO
	public PacienteCadastroDTO toPacienteCadastroDTO() {
		PacienteCadastroDTO dto = new PacienteCadastroDTO();
		dto.setUsername(username);
		dto.setNome(fullName);
		dto.setEmail(email);
		dto.setPassword(password);
		dto.setSexo(sexo);
		dto.setDataNascimento(dataNascimento);
		dto.setCpf(cpf);
		dto.setTelefone(telefone);
		dto.setEndereco(endereco);
		return dto;
	}
}
