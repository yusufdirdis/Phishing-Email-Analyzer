package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KeywordDetector {

    // List of phishing-related keywords
    private static final List<String> KEYWORDS = Arrays.asList(
            "urgent",
            "immediately",
            "verify",
            "password",
            "suspended",
            "account",
            "click"
    );

    public static List<String> detectKeywords(EmailData email) {

        List<String> matches = new ArrayList<>();

        // Prevent NullPointerException
        if (email == null || email.getBody() == null) {
            return matches;
        }

        // Convert the email body to lowercase
        String body = email.getBody().toLowerCase();

        // Check for suspicious keywords
        for (String keyword : KEYWORDS) {
            if (body.matches(".*\\b" + keyword + "\\b.*")) {
                matches.add("Suspicious Keyword: " + keyword);
            }
        }

        return matches;
    }
}