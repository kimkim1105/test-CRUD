package com.example.demospring.model.dto;

import com.example.demospring.model.Book;
import com.example.demospring.model.Order;
import com.example.demospring.model.Person;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class OrderDetailDTO {
    @NotNull(message = "person cannot null")
    private Order order;
    @NotNull(message = "book cannot null")
    private Book book;
    private LocalDate dateOn;
    private LocalDate dateOff;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(Order order, Book book, LocalDate dateOn, LocalDate dateOff) {
        this.order = order;
        this.book = book;
        this.dateOn = dateOn;
        this.dateOff = dateOff;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateOn() {
        return dateOn;
    }

    public void setDateOn(LocalDate dateOn) {
        this.dateOn = dateOn;
    }

    public LocalDate getDateOff() {
        return dateOff;
    }

    public void setDateOff(LocalDate dateOff) {
        this.dateOff = dateOff;
    }
}
