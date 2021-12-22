package me.missionfamily.web.mission_family_be.common.util;

import java.util.Random;

public final class RandomStringUtils {

    private static Random random = new Random();

    private static final int NUMERAL_ZERO = 48;
    private static final int LETTER_A = 97;
    private static final int LETTER_Z = 122;

    public static final String randomAlphabetic(int length) {

        return random.ints(LETTER_A, LETTER_Z + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static final String randomAlphanumeric(int length) {

        return random.ints(NUMERAL_ZERO, LETTER_Z +1)
                .filter(num -> (num <=57 || num >= 65) && (num <= 90 || num >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
