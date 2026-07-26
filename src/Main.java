import analyzer.LinkAnalyzer;
import model.AnalysisResult;
import model.EmailData;
import model.KeywordDetector;
import scoring.RiskScorer;
import test.TestData;

import java.util.ArrayList;

class Main {

     static void main() {

        System.out.println("===== PHISHING EMAIL ANALYZER =====");
        System.out.println();

        // Temporary input until EmailParser is completed
        EmailData email = TestData.getTestEmail();

        if (email == null) {
            System.out.println("Unable to load email.");
            return;
        }

        ArrayList<String> flags = new ArrayList<>();

        // Nubaid - Keyword Detection
        flags.addAll(KeywordDetector.detectKeywords(email));

        // Villeret - URL Analysis
        flags.addAll(LinkAnalyzer.analyzeLinks(email));

        // Josue - Risk Scoring
        RiskScorer riskScorer = new RiskScorer();
        AnalysisResult result = riskScorer.score(flags);

        // Display email information
        System.out.println("Sender: " + email.sender);
        System.out.println("Subject: " + email.subject);
        System.out.println();

        System.out.println("Detected Issues:");

        if (flags.isEmpty()) {
            System.out.println("- No suspicious indicators detected.");
        } else {
            for (String flag : flags) {
                System.out.println("- " + flag);
            }
        }

        System.out.println();
        System.out.println("Risk Score: " + result.score);
        System.out.println("Risk Level: " + result.riskLevel);

        System.out.println();
        System.out.println("===== ANALYSIS COMPLETE =====");
    }
}