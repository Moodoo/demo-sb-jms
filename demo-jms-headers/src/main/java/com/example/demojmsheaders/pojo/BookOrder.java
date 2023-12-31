package com.example.demojmsheaders.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class BookOrder {

    @JsonCreator
    public BookOrder(@JsonProperty("bookOrderId") String bookOrderId,@JsonProperty("book") Book book,
                     @JsonProperty("customer") Customer customer) {
        this.bookOrderId = bookOrderId;
        this.book = book;
        this.customer = customer;
    }

    private final String bookOrderId;
    private final Book book;
    private final Customer customer;

    public String getBookOrderId() {
        return bookOrderId;
    }

    public Book getBook() {
        return book;
    }

    public Customer getCustomer() {
        return customer;
    }
}
