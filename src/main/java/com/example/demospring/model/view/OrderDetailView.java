package com.example.demospring.model.view;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Immutable
@Table(name = "`order_search_detail`")
@Subselect("select sys_guid() as id, hs.* from order_search_detail hs")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "set_order_id_search_detail",
                procedureName = "PARAMS_PKG.set_order_id_search_detail",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_order_id", type = Long.class),
                })
})
public class OrderDetailView {
    @Id
    private String id;
    private Long orderDetailId;
    private LocalDate dateOff;
    private LocalDate dateOn;
    private Long bookId;
    private String bookName;
    private String bookCode;
    private Long orderId;
    private Integer overDate;

    public OrderDetailView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Long orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public LocalDate getDateOff() {
        return dateOff;
    }

    public void setDateOff(LocalDate dateOff) {
        this.dateOff = dateOff;
    }

    public LocalDate getDateOn() {
        return dateOn;
    }

    public void setDateOn(LocalDate dateOn) {
        this.dateOn = dateOn;
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

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Integer getOverDate() {
        return overDate;
    }

    public void setOverDate(Integer overDate) {
        this.overDate = overDate;
    }
}
