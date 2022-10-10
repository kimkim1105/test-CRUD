package com.example.demospring.model.view;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "`person_borrow_history`")
@Subselect("select sys_guid() as id, hs.* from person_borrow_history hs")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "set_key_person_history",
                procedureName = "PARAMS_PKG.set_key_person_history",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_key_param", type = String.class),
                }),
})
public class PersonHistory  implements Serializable {
    @Id
    private String id;
    private Long personId;
    private String personName;
    private String personCode;
    private String personPhone;
    private Integer completed;
    private Integer borrowing;
    private Integer overDate;

    public PersonHistory() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonCode() {
        return personCode;
    }

    public void setPersonCode(String personCode) {
        this.personCode = personCode;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getBorrowing() {
        return borrowing;
    }

    public void setBorrowing(Integer borrowing) {
        this.borrowing = borrowing;
    }

    public Integer getOverDate() {
        return overDate;
    }

    public void setOverDate(Integer overDate) {
        this.overDate = overDate;
    }
}
