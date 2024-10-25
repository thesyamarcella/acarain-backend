package com.acarain.service;

import com.acarain.model.Comment;
import com.acarain.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsForEvent(Long eventId) {
        return commentRepository.findByEventId(eventId);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
}
