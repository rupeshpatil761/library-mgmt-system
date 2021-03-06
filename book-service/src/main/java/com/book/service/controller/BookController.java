package com.book.service.controller;

import com.book.service.model.Book;
import com.book.service.service.BookServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping
    public Book addBook(@RequestBody Book book){
        return bookService.addBook(book);
    }

    private Logger logger = LoggerFactory.getLogger(BookController.class);

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable(name="id", required = true) Long id){
        logger.info("getBookById book id : {}",id);
        return bookService.getBookById(id);
    }

    @GetMapping()
    public List<Book> getAllBooks(){
        logger.info("{}"," << getAllBooks");
        return bookService.getAllBooks();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable(name="id", required = true) Long id){
        bookService.deleteBook(id);
        return ResponseEntity.ok("Resource Delete");
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable(name="id", required = true) Long id, @RequestBody Book book){
        return bookService.updateBook(id,book);
    }

    /**
     * Added these two endpoints to test dynamo db operations
     * @param book
     * @return
     */
    @PostMapping("/dynamo")
    public com.book.service.model.dynamo.Book addBookDynamoDb(@RequestBody com.book.service.model.dynamo.Book book){
        return bookService.addBookToDynamoDb(book);
    }

    @GetMapping("/dynamo")
    public List<com.book.service.model.dynamo.Book> getAllBooksDynamoDb(){
        return bookService.getAllBooksDynamo();
    }

}
