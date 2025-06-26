package com.kitapkulubu.kitapapi.controller;

import com.kitapkulubu.kitapapi.model.Comment;
import com.kitapkulubu.kitapapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/{bookId}/comments")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> addComment(@PathVariable Long bookId, @RequestBody Comment comment) {
        return commentService.addCommentToBook(bookId, comment)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Comment> getComments(@PathVariable Long bookId) {
        return commentService.getCommentsByBookId(bookId);
    }
}
