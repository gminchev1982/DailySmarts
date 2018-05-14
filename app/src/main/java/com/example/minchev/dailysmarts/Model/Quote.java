package com.example.minchev.dailysmarts.Model;


public class Quote {

    private String quoteText;
    private String quoteAuthor;
    private String senderName;
    private String senderLink;
    private String quoteLink;

    public Quote() {
    }

    public Quote(String text, String author) {
        this.quoteText = text;
        this.quoteAuthor = author;
    }

    public static Quote getFakeQuote() {
        return new Quote("You should only use this for test purposes. It should be replaced with real data eventually.", "Teo");
    }

    public String getQuoteText() {
        return quoteText;
    }

    public String getQuoteAuthor() {
        return quoteAuthor;
    }

    public String getQuoteLink() {
        return quoteLink;
    }

}
