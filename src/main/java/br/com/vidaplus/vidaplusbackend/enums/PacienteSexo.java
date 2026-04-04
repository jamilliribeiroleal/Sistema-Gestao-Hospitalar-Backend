package br.com.vidaplus.vidaplusbackend.enums;

public enum PacienteSexo {
    M, F, O;

    public static PacienteSexo from(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Sexo não pode ser nulo");
        }

        return switch (value.trim().toUpperCase()) {
            case "M" -> M;
            case "F" -> F;
            case "O" -> O;
            default -> throw new IllegalArgumentException(
                "Sexo inválido. Use M, F ou O"
            );
        };
    }
}
