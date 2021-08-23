package com.book.service.service;

import com.book.service.model.dynamo.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    com.book.service.model.Book addBook(com.book.service.model.Book book);

    Book addBookToDynamoDb(Book book);

    public List<Book> getAllBooksDynamo();

    List<com.book.service.model.Book> getAllBooks();

    com.book.service.model.Book getBookById(Long id);

    com.book.service.model.Book updateBook(Long id, com.book.service.model.Book book);

    void deleteBook(Long id);
}
