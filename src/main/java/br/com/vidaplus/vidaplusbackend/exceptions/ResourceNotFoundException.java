package br.com.vidaplus.vidaplusbackend.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long id) {
        super(resourceName + " com id " + id + " não encontrado.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
