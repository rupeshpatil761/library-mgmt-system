package com.library.service.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity(name="library")
@Table(name="library")
@Getter
@Setter
@IdClass(LibraryId.class)
public class Library extends AuditModel {
    @Id
    private Long userId;
    @Id
    private Long bookId;

    public Library(){}

    public Library(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
