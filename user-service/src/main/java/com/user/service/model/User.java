package com.user.service.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity( name = "library_user")
@Table(name = "library_user")
@Getter
@Setter
@ToString
public class User extends AuditModel {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @Column(name="email",unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserType userType;

}

enum UserType {
    USER,
    ADMIN;
}
