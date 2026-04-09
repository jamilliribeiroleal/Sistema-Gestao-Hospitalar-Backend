package br.com.vidaplus.vidaplusbackend.services;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

	@Value("${app.crypto.secret}")
	private String secret;

//  AES COMPATÍVEL COM MYSQL 
	public byte[] encryptMySQL(String value) {
		if (value == null)
			return null;
		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.ENCRYPT_MODE, getMySQLKey());
			return cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao criptografar (MySQL AES)", e);
		}
	}

// AES COMPATÍVEL COM MYSQL 
	public String decryptMySQL(byte[] encrypted) {
		if (encrypted == null)
			return "-";
		try {
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);
			cipher.init(Cipher.DECRYPT_MODE, getMySQLKey());
			return new String(cipher.doFinal(encrypted), StandardCharsets.UTF_8);
		} catch (Exception e) {
			return "[Erro ao descriptografar]";
		}
	}

	// CHAVE AES IGUAL AO MYSQL
	private SecretKeySpec getMySQLKey() {
		byte[] keyBytes = Arrays.copyOf(secret.getBytes(StandardCharsets.UTF_8), 16);
		return new SecretKeySpec(keyBytes, ALGORITHM);
	}

//  HASH SHA-256 (para CPF, busca e unicidade)
	public String hash(String value) {
		if (value == null)
			return null;
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hashBytes = digest.digest(value.getBytes(StandardCharsets.UTF_8));

			StringBuilder sb = new StringBuilder();
			for (byte b : hashBytes) {
				sb.append(String.format("%02x", b));
			}
			return sb.toString();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao gerar hash SHA-256", e);
		}
	}
}
