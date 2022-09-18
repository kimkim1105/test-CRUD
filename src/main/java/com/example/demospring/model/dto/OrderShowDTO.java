package com.example.demospring.model.dto;

import java.time.LocalDate;

public class OrderShowDTO {
    private Long id;
    private String personCode;
    private String personName;
    private Integer bookCount;
    private LocalDate dateOn;
    private LocalDate dateOff;

    public OrderShowDTO() {
    }

    public OrderShowDTO(Long id, String personCode, String personName, Integer bookCount, LocalDate dateOn, LocalDate dateOff) {
        this.id = id;
        this.personCode = personCode;
        this.personName = personName;
        this.bookCount = bookCount;
        this.dateOn = dateOn;
        this.dateOff = dateOff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Integer getBookCount() {
        return bookCount;
    }

    public void setBookCount(Integer bookCount) {
        this.bookCount = bookCount;
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
