package com.example.demospring.model.dto;

import com.example.demospring.model.Book;
import com.example.demospring.model.Person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

public class OrderDTO {
    @NotNull(message = "person is not choosen")
    private Person person;

    public OrderDTO() {
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
