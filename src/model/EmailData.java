package model;

import java.util.ArrayList;
import java.util.List;

public class EmailData {

    public String sender;
    public String subject;
    public String body;
    public List<String> links;

    public EmailData() {
        links = new ArrayList<>();
    }
}