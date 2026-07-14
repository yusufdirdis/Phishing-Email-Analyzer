import analyzer.KeywordAnalyzer;
import analyzer.LinkAnalyzer;
import model.AnalysisResult;
import model.EmailData;
import parser.EmailParser;
import scoring.RiskScorer;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Read email
        EmailData email = EmailParser.parseEmail("email.txt");

        if (email == null) {
            System.out.println("Unable to read email.");
            return;
        }

        List<String> flags = new ArrayList<>();

        // Keyword Analysis (Nubaid)
        flags.addAll(
                KeywordAnalyzer.detectKeywords(email)
        );

        // URL Analysis (Villeret)
        flags.addAll(
                LinkAnalyzer.analyzeLinks(email)
        );

        // Risk Score (Josue)
        AnalysisResult result =
                RiskScorer.score(flags);

        // Final Output
        System.out.println("===== Phishing Email Analysis =====");
        System.out.println();

        System.out.println("Sender: " + email.sender);
        System.out.println();

        System.out.println("Flags Found:");

        if (flags.isEmpty()) {
            System.out.println("None");
        } else {
            for (String flag : flags) {
                System.out.println("- " + flag);
            }
        }

        System.out.println();

        System.out.println("Risk Score: " + result.score);

        System.out.println("Risk Level: " + result.riskLevel);
    }
}