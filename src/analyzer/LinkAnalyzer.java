package analyzer;

import model.EmailData;

import java.util.ArrayList;
import java.util.List;

public class LinkAnalyzer {

    public static List<String> analyzeLinks(EmailData email) {

        List<String> flags = new ArrayList<>();

        if (email == null || email.links == null) {
            return flags;
        }

        for (String url : email.links) {

            String lower = url.toLowerCase();

            if (lower.startsWith("http://")) {
                flags.add("Insecure URL: " + url);
            }

            if (lower.matches(".*\\d.*")) {
                flags.add("Suspicious URL (contains numbers): " + url);
            }

            if (lower.contains("login") ||
                    lower.contains("verify") ||
                    lower.contains("secure") ||
                    lower.contains("update") ||
                    lower.contains("account")) {

                flags.add("Suspicious keyword in URL: " + url);
            }

            if (lower.contains("paypa1") ||
                    lower.contains("g00gle") ||
                    lower.contains("arnazon")) {

                flags.add("Possible typosquatting: " + url);
            }
        }

        return flags;
    }
}