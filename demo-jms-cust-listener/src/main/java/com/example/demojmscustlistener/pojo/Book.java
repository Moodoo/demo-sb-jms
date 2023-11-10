package com.example.demojmscustlistener.pojo;

import lombok.ToString;

@ToString
public class Book {
    public Book(String bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }

    public Book() {
    }

    private  String bookId;
    private  String title;

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }
}
