package com.library.service.controller;

import com.library.service.bean.Book;
import com.library.service.bean.User;
import com.library.service.bean.UserBook;
import com.library.service.service.LibraryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library")
public class LibraryController {

    private Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryServiceImpl libraryService;

    @Autowired
    private Resilience4JCircuitBreakerFactory circuitBreakerFactory;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return libraryService.getAllUsers();
    }

    @GetMapping("/users/{id}")
    public UserBook getUserById(@PathVariable(name="id", required = true) Long id){
        return libraryService.getUserBookAssociationByUserId(id);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name="id", required = true) Long id){
        return libraryService.deleteUser(id);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable(name="id", required = true) Long id, @RequestBody User user){
        return libraryService.updateUser(id,user);
    }

    @GetMapping("/books/{id}")
    public Object getBookById(@PathVariable(name="id", required = true) Long id){
        logger.info("{}"," << getBookById + "+id);
        return libraryService.getBookById(id);
        //return circuitBreakerFactory.create("book").run(() -> libraryService.getBookById(id), throwable -> serviceUnavailableResponse());
    }

    private ResponseEntity<Object> serviceUnavailableResponse(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Book Service is Unavailable");
        //return new ResponseEntity<>()"Service is unavailable";
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(){
        return libraryService.getAllBooks();
    }

    /***
     * @param book
     * @apiNote This API will be called by ADMIN to add new book
     * @return Book
     */
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        return libraryService.addBook(book);
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable(name="id", required = true) Long id){
        return libraryService.deleteBook(id);
    }

    @PostMapping("/users")
    public User addUser(@RequestBody User user){
        return libraryService.addUser(user);
    }

    @PostMapping("/users/{user_id}/books/{book_id}")
    public UserBook issueBookToUser(@PathVariable(name="user_id", required = true) Long userId
            ,@PathVariable(name="book_id", required = true) Long bookId){
        return libraryService.issueBookToUser(userId,bookId);
    }

    @DeleteMapping("/users/{user_id}/books/{book_id}")
    public UserBook releaseBookForUser(@PathVariable(name="user_id", required = true) Long userId
            ,@PathVariable(name="book_id", required = true) Long bookId){
        return libraryService.releaseBookForUser(userId,bookId);
    }
}
