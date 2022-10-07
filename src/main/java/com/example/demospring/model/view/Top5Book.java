package com.example.demospring.model.view;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Immutable
@Table(name = "`top5_book_borrowed`")
@Subselect("select sys_guid() as id, hs.* from top5_book_borrowed hs")
public class Top5Book  implements Serializable {
    @Id
    private String id;
    private Long bookId;
    private String bookName;
    private String bookCode;
    private String category;
    private Integer countTurn;

    public Top5Book() {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getCountTurn() {
        return countTurn;
    }

    public void setCountTurn(Integer countTurn) {
        this.countTurn = countTurn;
    }
}
