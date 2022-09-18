package com.example.demospring.model.dto;

import com.example.demospring.model.Book;

public class BookOrderDTO {
    private Iterable<Book> bookIterable;

    public BookOrderDTO(Iterable<Book> bookIterable) {
        this.bookIterable = bookIterable;
    }

    public BookOrderDTO() {
    }

    public Iterable<Book> getBookIterable() {
        return bookIterable;
    }

    public void setBookIterable(Iterable<Book> bookIterable) {
        this.bookIterable = bookIterable;
    }
}
