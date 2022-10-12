package com.example.demospring.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "addNewPerson",
                procedureName = "PERSON_CRUD_PKG.addNewPerson",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_address", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_dob", type = LocalDate.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_gender", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_phone", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_classify_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_result", type = String.class),
                }),
        @NamedStoredProcedureQuery(name = "updatePerson",
                procedureName = "PERSON_CRUD_PKG.updatePerson",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_address", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_dob", type = LocalDate.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_gender", type = Boolean.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_phone", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_classify_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_person_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_result", type = String.class),
                }),
//        @NamedStoredProcedureQuery(name = "mergePerson",
//                procedureName = "PERSON_CRUD_PKG.mergePerson",
//                parameters = {
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_id", type = Long.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_address", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_dob", type = LocalDate.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_gender", type = Boolean.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_name", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_phone", type = String.class),
//                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_classify_id", type = Long.class),
//                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_result", type = String.class),
//                }),
        @NamedStoredProcedureQuery(name = "deletePerson",
                procedureName = "PERSON_CRUD_PKG.deletePerson",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_person_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_result", type = String.class),
                })

})

public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;
    @NotNull
    @NotEmpty
    private String code;
    @NotNull
    @NotEmpty
    private String name;
    @NotNull
    private boolean gender;
    @NotNull
    private boolean status;
    private String address;
    @NotNull
    @NotEmpty
    private String phone;
    @NotNull
    private LocalDate dateOfBirth;
//    @Lob
//    private String avatar;
//    private boolean typeAction;
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

//    public String getAvatar() {
//        return avatar;
//    }
//
//    public void setAvatar(String avatar) {
//        this.avatar = avatar;
//    }

//    public boolean isTypeAction() {
//        return typeAction;
//    }
//
//    public void setTypeAction(boolean typeAction) {
//        this.typeAction = typeAction;
//    }

    public Classify getClassify() {
        return classify;
    }

    public void setClassify(Classify classify) {
        this.classify = classify;
    }
}
