package com.uah.f1backend.common;

public class GenericValidations {

    private static final String urlRegex = "^(https?://)?(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_\\+.~#?&//=]+\\.(jpg|jpeg|png|gif))$|^(\\.\\./|[.]/)?[a-zA-Z0-9_/\\-]+\\.(jpg|jpeg|png|gif)$";

    private static final String twitterRegex = "^@([a-zA-Z0-9_]{1,15})$";

    public static Boolean isValidUrl(String candidate) {
        return candidate.matches(urlRegex);
    }

    public static Boolean isValidTwitter(String candidate) {
        return candidate.matches(twitterRegex);
    }
}
