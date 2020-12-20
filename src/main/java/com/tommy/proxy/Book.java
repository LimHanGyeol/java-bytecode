package com.tommy.proxy;

public class Book {

    private long id;

    private final String title;

    public Book(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
