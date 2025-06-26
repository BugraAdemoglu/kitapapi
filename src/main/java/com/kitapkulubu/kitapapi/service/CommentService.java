package com.kitapkulubu.kitapapi.service;

import com.kitapkulubu.kitapapi.model.Book;
import com.kitapkulubu.kitapapi.model.Comment;
import com.kitapkulubu.kitapapi.Repository.BookRepository;
import com.kitapkulubu.kitapapi.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BookRepository bookRepository;

    public Optional<Comment> addCommentToBook(Long bookId, Comment comment) {
        Optional<Book> book = bookRepository.findById(bookId);
        if (book.isPresent()) {
            comment.setBook(book.get());
            return Optional.of(commentRepository.save(comment));
        }
        return Optional.empty();
    }

    public List<Comment> getCommentsByBookId(Long bookId) {
        return commentRepository.findByBookId(bookId);
    }
}
