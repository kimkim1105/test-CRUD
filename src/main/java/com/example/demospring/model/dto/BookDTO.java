package com.example.demospring.model.dto;

import com.example.demospring.model.BookCategory;


public class BookDTO {
    private String code;
    private String name;
    private String author;
    private BookCategory category;

    public BookDTO() {
    }

    public BookDTO(String name, String author, BookCategory category) {
        this.name = name;
        this.author = author;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
