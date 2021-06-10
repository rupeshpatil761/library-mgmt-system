package com.library.service.bean;

import com.library.service.model.AuditModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Data
public class Book {

    private Long id;

    private String name;

    private String author;

    private BookCategory category;

    private String description;
}

enum BookCategory {
    FANTACY,
    THRILLER,
    SELF_HELP,
    MOTIVATIONAL,
    TRAVEL,
    SCIENCE,
    ROMANCE;
}
