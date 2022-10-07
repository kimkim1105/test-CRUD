package com.example.demospring.model.dto;

import com.example.demospring.model.Classify;

import javax.persistence.Column;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.*;
import java.time.LocalDate;

public class PersonDTO {
    @NotEmpty(message = "Name can't blank")
    @Pattern(regexp = "^([a-zA-Zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+)((\\s{1}[a-zA-Zỳọáầảấờễàạằệếýộậốũứĩõúữịỗìềểẩớặòùồợãụủíỹắẫựỉỏừỷởóéửỵẳẹèẽổẵẻỡơôưăêâđ]+){1,})$", message = "Name least 2 word with space seperate, without special character or number")
    private String name;
    private boolean gender;
    private String address;
    @NotEmpty(message = "Phone can't blank")
    @Pattern(regexp = "(0[3|5|7|8|9])+([0-9]{8})\\b", message = "incorrect fomat")
    @Column(unique = true)
    private String phone;
    @NotNull(message = "Date can't blank")
    @PastOrPresent(message = "date of birth must be in past")
    private LocalDate dateOfBirth;
//    private String avatar;
    @NotNull
    private Classify classify;

    public PersonDTO() {
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

//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
    }
}
