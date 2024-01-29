package net.ausiasmarch.serverTienda.helper;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.xml.bind.DatatypeConverter;

import net.ausiasmarch.serverTienda.exception.CannotPerformOperationException;

public class DataGenerationHelper {

    /*
     * Generates a random integer within the specified range.
     * 
     * @param min The minimum value.
     * @param max The maximum value.
     * @return A random integer.
     */
    public static int getRandomInt(int min, int max) {
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }

    /*
     * Calculates the SHA-256 hash of a given string.
     * 
     * @param strToHash The string to hash.
     * 
     * @return The SHA-256 hash of the input string.
     */
    public static String getSHA256(String strToHash) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(strToHash.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (NoSuchAlgorithmException ex) {
            throw new CannotPerformOperationException("no such algorithm: sha256");
        }
    }
    
}
