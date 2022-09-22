package com.example.demospring.model.dto;

import com.example.demospring.model.Book;
import com.example.demospring.model.Person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

public class OrderDTO {
    @NotNull(message = "Book is not choosen")
    @Size(min = 1, message = "choose book at least 1")
    private Collection<Book> book;
    @NotNull(message = "person is not choosen")
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
