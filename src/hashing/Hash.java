/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * @author Andrej
 */
public class Hash {

    private static String salt = "sviUBlokade";
    public static String kriptuj(String sifra) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String kombinacija = sifra + salt;
        byte[] kript = md.digest(kombinacija.getBytes());
        return Base64.getEncoder().encodeToString(kript);
    }

}
