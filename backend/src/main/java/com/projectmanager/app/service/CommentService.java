package com.projectmanager.app.service;

import com.projectmanager.app.model.Comment;
import com.projectmanager.app.model.Ticket;
import com.projectmanager.app.model.User;
import com.projectmanager.app.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    public List<Comment> getCommentsByTicketId(Long ticketId) {
        return commentRepository.findByTicketId(ticketId);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
    }

    public Comment createComment(Long ticketId, Long authorId, String content) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        User author = userService.getUserById(authorId);

        Comment comment = new Comment();
        comment.setTicket(ticket);
        comment.setAuthor(author);
        comment.setContent(content);

        return commentRepository.save(comment);
    }

    public Comment updateComment(Long id, String content) {
        Comment comment = getCommentById(id);
        comment.setContent(content);
        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
    }
}
