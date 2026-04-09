package br.com.vidaplus.vidaplusbackend.util;

import java.util.UUID;

public class IdGenerator {

    public static String generateToken() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }
}
