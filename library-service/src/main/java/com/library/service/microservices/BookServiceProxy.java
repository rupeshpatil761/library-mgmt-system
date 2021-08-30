package com.library.service.microservices;

import com.library.service.bean.Book;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="book-service",url="localhost:9092")//,fallbackFactory = BookServiceFallbackFactory.class
public interface BookServiceProxy {

    @GetMapping(value = "/books/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    Book getBookById(@PathVariable("id") Long id);

    @GetMapping("/books")
    List<Book> getAllBooks();

    @PostMapping("/books")
    Book addBook(@RequestBody Book book);

    @DeleteMapping("/books/{id}")
    ResponseEntity<?> deleteBook(@PathVariable(name="id", required = true) Long id);
}

