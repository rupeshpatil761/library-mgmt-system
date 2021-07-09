package com.book.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
public class Book extends AuditModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="name",unique = true,nullable = false)
    private String name;

    private String author;

    @Enumerated(EnumType.STRING)
    private BookCategory category;

    private String description;
}

