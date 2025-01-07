package com.example.proiectjavafinal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

///SE VA INTRODUCE MAI INCOLO CA SA MAI POT TESTA CHIESTII CU STUDENTI BAGATI DIN MAIN

public class SecuritateParole {
    public static String parolahashuita(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
