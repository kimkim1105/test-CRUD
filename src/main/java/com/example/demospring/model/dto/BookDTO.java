package com.example.demospring.model.dto;

import com.example.demospring.model.BookCategory;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class BookDTO {
    @NotEmpty(message = "code can't blank")
    @Column(unique = true)
    private String code;
    @NotEmpty(message = "Name can't blank")
    @Pattern(regexp = "^([a-vxyA-Z1-9ỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ\\s]+)$", message = "name without special character")
    @Column(unique = true)
    private String name;
    @NotEmpty(message = "author can't blank")
    @Pattern(regexp = "^([a-vxyA-Z1-9ỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ\\s]+)$", message = "author name without special character")
    private String author;
    @NotNull(message = "category can't null")
    private BookCategory category;
    @NotEmpty(message = "quantity can't null")
    @Pattern(regexp = "^[0-9]+$", message = "quantity must be native number ")
    private String inStock;

    public BookDTO() {
    }

    public String getInStock() {
        return inStock;
    }

    public void setInStock(String inStock) {
        this.inStock = inStock;
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
