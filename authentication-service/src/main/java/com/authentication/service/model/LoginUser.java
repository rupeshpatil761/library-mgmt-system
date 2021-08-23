package com.authentication.service.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Getter
@Entity
@ToString
public class LoginUser extends AuditModel {
    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    private Boolean isActive = true;
    private String role = "ADMIN";
}
