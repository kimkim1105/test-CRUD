package com.example.demospring.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Book book;
    private LocalDate dateOn;
    private LocalDate dateOff;
    private boolean status;

    public OrderDetail() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
