package com.example.demospring.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "person", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "code"
        }),
        @UniqueConstraint(columnNames = {
                "phone"
        })
})
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String code;
    @NotNull
    private String name;
    @NotNull
    private boolean gender;
    @NotNull
    private boolean status;
    private String address;
    @NotNull
    @Column(unique = true)
    private String phone;
    @NotNull
    private LocalDate dateOfBirth;
    @Lob
    private String avatar;
    private boolean typeAction;
    @ManyToOne
    @NotNull
    private Classify classify;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isTypeAction() {
        return typeAction;
    }

    public void setTypeAction(boolean typeAction) {
        this.typeAction = typeAction;
    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
    }
}
