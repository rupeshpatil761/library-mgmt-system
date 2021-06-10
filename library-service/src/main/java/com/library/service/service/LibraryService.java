package com.library.service.service;

import com.library.service.bean.Book;
import com.library.service.bean.User;
import com.library.service.bean.UserBook;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LibraryService {

    User getUserById(long id);
    UserBook getUserBookAssociationByUserId(long id);
    List<User> getAllUsers();
    User addUser(User user);
    ResponseEntity<?> deleteUser(Long id);
    User updateUser(Long id, User book);

    Book getBookById(long id);
    List<Book> getAllBooks();
    Book addBook(Book book);
    ResponseEntity<?> deleteBook(Long id);

    UserBook issueBookToUser(Long userId, Long bookId);
    UserBook releaseBookForUser(Long userId, Long bookId);
}
