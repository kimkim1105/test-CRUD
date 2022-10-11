package com.example.demospring.model.view;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "`book_borrow_history_detail`")
@Subselect("select sys_guid() as id, hs.* from book_borrow_history_detail hs")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "set_book_id",
                procedureName = "PARAMS_PKG.set_book_id",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_book_id", type = Long.class),
                }),
        @NamedStoredProcedureQuery(name = "set_key_person_search_book_history",
                procedureName = "PARAMS_PKG.set_key_person_search_book_history",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_key_param", type = String.class),
                })
})
public class BookHistoryDetail {
    @Id
    private String id;
    private Long personId;
    private String personName;
    private String personCode;
    private Long orderId;
    private Long bookId;
    private Integer numberOfTurn;

    public BookHistoryDetail() {
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Integer getNumberOfTurn() {
        return numberOfTurn;
    }

    public void setNumberOfTurn(Integer numberOfTurn) {
        this.numberOfTurn = numberOfTurn;
    }
}
