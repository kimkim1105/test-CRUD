package com.example.demospring.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Person person;
    private Integer bookSize;

    public Order() {
    }

    public Integer getBookSize() {
        return bookSize;
    }

    public void setBookSize(Integer bookSize) {
        this.bookSize = bookSize;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
