package icu.yfd.utils;

import java.security.SecureRandom;

public class Random {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    int length;

    public Random(int length) {
        this.length = length;
    }

    public Random(int length, String characters) {
        this.length = length;
        this.characters = characters;
    }

    public String generate() {
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(
                    characters.charAt(
                            new SecureRandom()
                                    .nextInt(characters.length())
                    )
            );
        }

        return sb.toString();
    }
}