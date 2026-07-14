package analyzer;

import model.EmailData;

import java.util.ArrayList;
import java.util.List;

public class LinkAnalyzer {

    public static List<String> analyzeLinks(EmailData data) {

        List<String> flags = new ArrayList<>();

        if (data == null || data.links == null) {
            return flags;
        }

        for (String url : data.links) {

            String lower = url.toLowerCase();

            // HTTP instead of HTTPS
            if (lower.startsWith("http://")) {
                flags.add("Insecure URL: " + url);
            }

            // Numbers inside the domain (simple heuristic)
            if (lower.matches(".*\\d.*")) {
                flags.add("Suspicious URL (contains numbers): " + url);
            }

            // Common phishing words
            if (lower.contains("login") ||
                    lower.contains("verify") ||
                    lower.contains("secure") ||
                    lower.contains("update") ||
                    lower.contains("account")) {

                flags.add("Suspicious keyword in URL: " + url);
            }

            // Typosquatting examples
            if (lower.contains("paypa1") ||
                    lower.contains("g00gle") ||
                    lower.contains("arnazon")) {

                flags.add("Possible typosquatting: " + url);
            }
        }

        return flags;
    }
}