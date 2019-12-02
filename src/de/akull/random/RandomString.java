package de.akull.random;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RandomString {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    // https://www.owasp.org/index.php/Password_special_characters
    private static final String SPECIAL = "!\"#$%&'()*+,-./:;<=>?@[\\]^_`{|}~";
    private static final String ALL = UPPERCASE + LOWERCASE + NUMBERS + SPECIAL;

    private static final SecureRandom random = new SecureRandom();

    public static String generate(int nUppercase, int nLowercase, int nNumbers, int nSpecial, int maxlength) {
        int fill = maxlength - (nUppercase + nLowercase + nNumbers + nSpecial);
        if (fill < 0) {
            throw new IllegalArgumentException();
        }
        return shuffle(
            selectRandomTokens(nUppercase, UPPERCASE)
                + selectRandomTokens(nLowercase, LOWERCASE)
                + selectRandomTokens(nNumbers, NUMBERS)
                + selectRandomTokens(nSpecial, SPECIAL)
                + selectRandomTokens(fill, ALL)
        );
    }

    private static String selectRandomTokens(int n, String tokens) {
        StringBuilder randomTokens = new StringBuilder();

        for (int i = 0; i < n; i++) {
            randomTokens.append(tokens.charAt(random.nextInt(tokens.length())));
        }
        return randomTokens.toString();
    }

    private static String shuffle(String s) {
        List<String> tokens = Arrays.asList(s.split(""));
        Collections.shuffle(tokens);
        return String.join("", tokens);
    }
}
