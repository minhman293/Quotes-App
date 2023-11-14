package com.man293.quotesapp.models;

public class Quote {
    private String content, author;

    // constructor
    public Quote(String content, String author) {
        this.content = content;
        this.author = author;
    }

    // getter and setter
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
