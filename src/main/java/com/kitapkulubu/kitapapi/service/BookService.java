package com.kitapkulubu.kitapapi.service;

import com.kitapkulubu.kitapapi.Repository.AuthorRepository;
import com.kitapkulubu.kitapapi.Repository.PublishHouseRepository;
import com.kitapkulubu.kitapapi.dto.BookDTO;
import com.kitapkulubu.kitapapi.model.*;
import com.kitapkulubu.kitapapi.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    @Autowired
    private PublishHouseRepository publishHouseRepository;


    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Book saveBook(BookDTO bookDTO) {
        Optional<Author> optionalAuthor = authorRepository.findById(bookDTO.getAuthorId());
        Optional<PublishHouse> optionalPublishHouse = publishHouseRepository.findById(bookDTO.getPublishHouseId());

        if (optionalAuthor.isEmpty() || optionalPublishHouse.isEmpty()) {
            throw new RuntimeException("Author or PublishHouse not found.");
        }

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setDescription(bookDTO.getDescription());
        book.setPublicationYear(bookDTO.getPublicationYear());
        book.setAuthor(optionalAuthor.get());
        book.setPublishHouse(optionalPublishHouse.get());
        book.setPublicationYear(bookDTO.getPublicationYear());

        return bookRepository.save(book);
    }
    public Book updateBook(Long id, BookDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        Author author = authorRepository.findById(bookDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));

        PublishHouse publishHouse = publishHouseRepository.findById(bookDTO.getPublishHouseId())
                .orElseThrow(() -> new RuntimeException("Publish house not found"));

        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setDescription(bookDTO.getDescription());
        existingBook.setPublicationYear(bookDTO.getPublicationYear());
        existingBook.setAuthor(author);
        existingBook.setPublishHouse(publishHouse);

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
    public List<Book> getBooksSortedByRating() {
        List<Book> books = bookRepository.findAll();
        books.sort((b1, b2) -> Double.compare(
                b2.getRates().stream().mapToDouble(Rate::getScore).average().orElse(0.0),
                b1.getRates().stream().mapToDouble(Rate::getScore).average().orElse(0.0)
        ));
        return books;
    }
}
