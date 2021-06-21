package com.book.service.service;

import com.book.service.exception.ResourceNotFoundException;
import com.book.service.model.Book;
import com.book.service.repository.BookRepository;
import com.book.service.repository.dynamo.BookRepositoryDynamo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookRepositoryDynamo bookRepositoryDynamo;

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public com.book.service.model.dynamo.Book addBookToDynamoDb(com.book.service.model.dynamo.Book book) {
        return bookRepositoryDynamo.save(book);
    }

    @Override
    public List<com.book.service.model.dynamo.Book> getAllBooksDynamo() {
        return (List<com.book.service.model.dynamo.Book>) bookRepositoryDynamo.findAll();
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    public Book updateBook(Long id, Book requestedBook) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        book.setName(requestedBook.getName());
        book.setAuthor(requestedBook.getAuthor());
        book.setCategory(requestedBook.getCategory());
        book.setDescription(requestedBook.getDescription());
        return bookRepository.save(book);
    }

    @Override
    public ResponseEntity<?> deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        bookRepository.delete(book);
        return ResponseEntity.ok().build();

    }
}
