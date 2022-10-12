package com.example.demospring.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "personmerge", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "code"
        }),
        @UniqueConstraint(columnNames = {
                "phone"
        })
})
public class PersonMerge {
    @Id
//    @GeneratedValue(strategy= GenerationType.IDENTITY)
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
}
