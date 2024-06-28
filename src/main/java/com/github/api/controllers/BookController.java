package com.github.api.controllers;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.api.models.Book;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public ResponseEntity<String> getAllBooks() {
        return ResponseEntity.ok("All books");
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getBookById(@PathVariable int id) {
        return ResponseEntity.ok("Book with ID " + id);
    }

    @PostMapping
    public ResponseEntity<String> createBook(@RequestBody Book book) {
        return ResponseEntity.status(201).body("Book was created: " + book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(
        @PathVariable int id,    
        @RequestBody Book book
    ) {
        return ResponseEntity.ok("Book with id " + id + " was updated: " + book); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        return ResponseEntity.noContent().build();
    }
}
