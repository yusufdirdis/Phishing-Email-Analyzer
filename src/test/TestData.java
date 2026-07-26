package test;

import model.EmailData;

public class TestData {

    public static EmailData getTestEmail() {

        EmailData email = new EmailData();

        email.sender = "support@paypal.com";

        email.subject = "Urgent: Verify Your Account";

        email.body =
                "Dear Customer,\n\n" +
                        "Your PayPal account has been temporarily limited.\n" +
                        "Please verify your account immediately by clicking the link below.\n\n" +
                        "Failure to do so within 24 hours will result in account suspension.";

        email.links.add(
                "http://paypa1-secure.com/login"
        );

        email.links.add(
                "https://www.paypal.com"
        );

        return email;
    }
}