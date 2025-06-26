package com.kitapkulubu.kitapapi.controller;

import com.kitapkulubu.kitapapi.dto.BookDTO;
import com.kitapkulubu.kitapapi.model.Book;
import com.kitapkulubu.kitapapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody BookDTO book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDTO));
    }

    @GetMapping("/sort=rating")
    public List<Book> getBooksSortedByRating() {
        return bookService.getBooksSortedByRating();
    }
}