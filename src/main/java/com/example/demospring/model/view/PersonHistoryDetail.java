package com.example.demospring.model.view;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "`person_borrow_history_detail`")
@Subselect("select sys_guid() as id, hs.* from person_borrow_history_detail hs")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "set_order_id",
                procedureName = "PARAMS_PKG.set_order_id",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_person_id", type = Long.class),
                }),
        @NamedStoredProcedureQuery(name = "set_key_book_person_history",
                procedureName = "PARAMS_PKG.set_key_book_person_history",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_key_param", type = String.class),
                })
})
public class PersonHistoryDetail  implements Serializable {
    @Id
    private String id;
    private Long bookId;
    private String bookName;
    private String bookCode;
    private LocalDate dateOn;
    private LocalDate dateOff;
    private Long orderDetailId;
    private Integer countDate;

    public PersonHistoryDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookCode() {
        return bookCode;
    }

    public void setBookCode(String bookCode) {
        this.bookCode = bookCode;
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

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public Integer getCountDate() {
        return countDate;
    }

    public void setCountDate(Integer countDate) {
        this.countDate = countDate;
    }
}
