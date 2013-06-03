package net.alexoro.androidutils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User: alexoro
 * Date: 04.06.13
 * Time: 2:02
 */
public class MD5 {

    private MD5() {
    }

    /**
     * Calculate MD5 for given string
     *
     * @param s
     * @return
     */
    public static String encode(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder(messageDigest.length * 2);
            for (int i = 0; i < messageDigest.length; i++) {
                String p = Integer.toHexString(0xFF & messageDigest[i]);
                while (p.length() < 2) {
                    p = "0" + p;
                }
                hexString.append(p);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Cannot init md5 algorithm", e);
        }
    }

}