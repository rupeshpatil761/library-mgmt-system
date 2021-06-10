package com.library.service.bean;

import java.util.ArrayList;
import java.util.List;

public class UserBook {
    public User user;
    public List<Book> bookList = new ArrayList<>();

    public UserBook(){

    }
    public UserBook(User user) {
        this.user = user;
    }
}
