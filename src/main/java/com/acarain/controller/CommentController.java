package com.acarain.controller;

import com.acarain.model.Comment;
import com.acarain.model.Review;
import com.acarain.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment) {
        Comment savedComment = commentService.addComment(comment);
        return ResponseEntity.ok(savedComment);
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<List<Comment>> getReviewsForEvent(@PathVariable Long eventId) {
        List<Comment> comments = commentService.getCommentsForEvent(eventId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments() {
        List<Comment> comments = commentService.getAllComments();
        return ResponseEntity.ok(comments);
    }
}
