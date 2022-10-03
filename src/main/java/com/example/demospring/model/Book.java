package com.example.demospring.model;


import com.sun.istack.NotNull;
import jdk.jfr.Enabled;

import javax.persistence.*;

@Entity
@Table(name = "book", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "code"
        })
//        @UniqueConstraint(columnNames = {
//                "name"
//        })
})
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(name = "addNewBook",
                procedureName = "BOOK_CRUD_PKG.addNewBook",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_author", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_code", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_inStock", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_category_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_result", type = String.class),
                }),
        @NamedStoredProcedureQuery(name = "updateBook",
                procedureName = "BOOK_CRUD_PKG.updateBook",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_author", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_name", type = String.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_category_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_book_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_result", type = String.class),
                }),
        @NamedStoredProcedureQuery(name = "deleteBook",
                procedureName = "BOOK_CRUD_PKG.deleteBook",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, name = "v_book_id", type = Long.class),
                        @StoredProcedureParameter(mode = ParameterMode.OUT, name = "v_result", type = String.class),
                })

})
public class Book {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;
    @NotNull
    private String code;
    @NotNull
    private boolean status;
    @NotNull
    private String name;
    @NotNull
    private String author;
    @ManyToOne
    @NotNull
    private BookCategory category;
    @NotNull
    private Integer inStock;


    public Book() {
    }

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
