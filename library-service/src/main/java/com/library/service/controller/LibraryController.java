package com.library.service.controller;

import com.library.service.bean.Book;
import com.library.service.bean.User;
import com.library.service.bean.UserBook;
import com.library.service.model.Library;
import com.library.service.service.LibraryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("library")
public class LibraryController {

    @Autowired
    private LibraryServiceImpl libraryService;

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
    public Book getBookById(@PathVariable(name="id", required = true) Long id){
        return libraryService.getBookById(id);
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
