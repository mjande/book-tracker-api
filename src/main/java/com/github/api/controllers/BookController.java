package com.github.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping
    public String getAllBooks() {
        return "All books";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable int id) {
        return "Book with ID " + id;
    }
}
