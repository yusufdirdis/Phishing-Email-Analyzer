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

    public static List<String> detectKeywords(model.EmailData email) {

        List<String> matches = new ArrayList<>();

        // Prevent NullPointerException
        if (email == null) {
            return matches;
        }

        // Check both subject and body
        String subject = email.subject == null
                ? ""
                : email.subject.toLowerCase();

        String body = email.body == null
                ? ""
                : email.body.toLowerCase();

        // Search for suspicious keywords
        for (String keyword : KEYWORDS) {

            if (subject.contains(keyword) || body.contains(keyword)) {
                matches.add("Suspicious Keyword: " + keyword);
            }
        }

        return matches;
    }
}