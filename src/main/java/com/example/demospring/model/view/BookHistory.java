package com.example.demospring.model.view;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "`book_borrow_history`")
@Subselect("select sys_guid() as id, hs.* from book_borrow_history hs")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "set_key_book_history",
                procedureName = "PARAMS_PKG.set_key_book_history",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_key_param", type = String.class),
                }),
})
public class BookHistory {
    @Id
    private String id;
    private Long bookId;
    private String bookName;
    private String bookCode;
    private Integer bookAvailable;
    private Integer bookBorrowing;
    private Integer bookOverDate;

    public BookHistory() {
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

    public Integer getBookAvailable() {
        return bookAvailable;
    }

    public void setBookAvailable(Integer bookAvailable) {
        this.bookAvailable = bookAvailable;
    }

    public Integer getBookBorrowing() {
        return bookBorrowing;
    }

    public void setBookBorrowing(Integer bookBorrowing) {
        this.bookBorrowing = bookBorrowing;
    }

    public Integer getBookOverDate() {
        return bookOverDate;
    }

    public void setBookOverDate(Integer bookOverDate) {
        this.bookOverDate = bookOverDate;
    }
}
