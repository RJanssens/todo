package com.projectmanager.app.controller;

import com.projectmanager.app.model.Comment;
import com.projectmanager.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/ticket/{ticketId}")
    public ResponseEntity<List<Comment>> getCommentsByTicketId(@PathVariable Long ticketId) {
        return ResponseEntity.ok(commentService.getCommentsByTicketId(ticketId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Map<String, Object> request) {
        Long ticketId = Long.valueOf(request.get("ticketId").toString());
        Long authorId = Long.valueOf(request.get("authorId").toString());
        String content = request.get("content").toString();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(commentService.createComment(ticketId, authorId, content));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable Long id,
            @RequestBody Map<String, String> request) {
        String content = request.get("content");
        return ResponseEntity.ok(commentService.updateComment(id, content));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}
