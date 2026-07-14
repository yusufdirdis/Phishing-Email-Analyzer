package test;

import model.EmailData;
import java.util.List;

public class TestData {

    public static EmailData getTestEmail() {
        EmailData data = new EmailData();

        data.sender = "support@paypa1-secure.com";

        data.body = """
        URGENT: Verify Your Account

        Dear user,
        You must verify your account immediately or it will be suspended.
        """;

        data.links = List.of("http://paypa1-secure.com/login");

        return data;
    }
}