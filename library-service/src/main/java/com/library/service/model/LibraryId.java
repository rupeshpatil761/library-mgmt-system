package com.library.service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Data
public class LibraryId implements Serializable {
    private Long userId;
    private Long bookId;

    public LibraryId(){}

    public LibraryId(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }
}
