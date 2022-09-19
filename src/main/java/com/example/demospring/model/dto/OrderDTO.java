package com.example.demospring.model.dto;

import com.example.demospring.model.Book;
import com.example.demospring.model.Person;

import javax.persistence.ManyToOne;
import java.util.Collection;

public class OrderDTO {
    private Collection<Book> book;
    private Person person;

    public OrderDTO() {
    }

    public OrderDTO(Collection<Book> book, Person person) {
        this.book = book;
        this.person = person;
    }

    public Collection<Book> getBook() {
        return book;
    }

    public void setBook(Collection<Book> book) {
        this.book = book;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
