package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//1S-->MBook -->OneToMany
//MB-->1S -->ManyToOne
@Entity
@Getter
@Setter

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @JsonProperty("bookName")
    //sadece json formarina donusturulurken fieldin belirtilenkey ile gosterilmesini saglar
    private String name;

    @ManyToOne
    @JsonIgnore
    //bu fieldi JSON formatinda ignore et(gormezden gel)
    private Student student;
}
