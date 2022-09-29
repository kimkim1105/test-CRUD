package com.example.demospring.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class Classify {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "id_Sequence")
//    @SequenceGenerator(name = "id_Sequence", sequenceName = "ID_SEQ")
    private Long id;
    @NotNull
    @Column(unique = true)
    private String code;
    private String classify;

    public Classify() {
    }

    public Classify(Long id, String faculity) {
        this.id = id;
        this.classify = faculity;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }
}
