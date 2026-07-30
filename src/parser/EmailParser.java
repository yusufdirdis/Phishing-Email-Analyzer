package parser;

import model.EmailData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailParser {

    public static EmailData parseEmail(String fileName) {

        EmailData email = new EmailData();

        StringBuilder bodyBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            boolean bodyStarted = false;

            while ((line = reader.readLine()) != null) {

                // Sender
                if (line.startsWith("From:")) {
                    email.sender = line.substring(5).trim();
                    continue;
                }

                // Subject
                if (line.startsWith("Subject:")) {
                    email.subject = line.substring(8).trim();
                    continue;
                }

                // Empty line means body starts
                if (line.trim().isEmpty()) {
                    bodyStarted = true;
                    continue;
                }

                if (bodyStarted) {
                    bodyBuilder.append(line).append("\n");

                    // Find URLs
                    Pattern pattern = Pattern.compile("(https?://\\S+)");
                    Matcher matcher = pattern.matcher(line);

                    while (matcher.find()) {
                        email.links.add(matcher.group());
                    }
                }
            }

            email.body = bodyBuilder.toString();

        }
        catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
            e.printStackTrace();
        }

        return email;
    }
}