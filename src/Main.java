import analyzer.LinkAnalyzer;
import model.AnalysisResult;
import model.EmailData;
import model.KeywordDetector;
import parser.EmailParser;
import scoring.RiskScorer;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("===== PHISHING EMAIL ANALYZER =====");
        System.out.println();

        System.out.print("Enter email file name: ");
        String fileName = scanner.nextLine();

        EmailData email = EmailParser.parseEmail(fileName);

        if (email == null) {
            System.out.println("Unable to analyze email.");
            return;
        }

        ArrayList<String> flags = new ArrayList<>();

        // Keyword Detection
        flags.addAll(KeywordDetector.detectKeywords(email));

        // URL Analysis
        flags.addAll(LinkAnalyzer.analyzeLinks(email));

        // Risk Scoring
        RiskScorer riskScorer = new RiskScorer();
        AnalysisResult result = riskScorer.score(flags);

        // Display Email Information
        System.out.println();
        System.out.println("===== EMAIL INFORMATION =====");
        System.out.println("Sender : " + email.sender);
        System.out.println("Subject: " + email.subject);

        // Display Links Found
        System.out.println();
        System.out.println("===== LINKS FOUND =====");

        if (email.links.isEmpty()) {
            System.out.println("No links found.");
        } else {
            for (String link : email.links) {
                System.out.println("- " + link);
            }
        }

        // Display Issues
        System.out.println();
        System.out.println("===== DETECTED ISSUES =====");

        if (flags.isEmpty()) {
            System.out.println("No suspicious indicators detected.");
        } else {
            for (String flag : flags) {
                System.out.println("- " + flag);
            }
        }

        // Display Results
        System.out.println();
        System.out.println("===== ANALYSIS RESULT =====");
        System.out.println("Risk Score : " + result.score);
        System.out.println("Risk Level : " + result.riskLevel);

        System.out.println();
        System.out.println("===== ANALYSIS COMPLETE =====");

        scanner.close();
    }
}