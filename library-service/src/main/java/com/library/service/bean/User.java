package com.library.service.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {
    private Long id;
    private String name;
    private String email;
    private UserType userType;
}
enum UserType {
    USER,
    ADMIN;
}

