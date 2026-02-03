package kanban_task_manager.comment.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kanban_task_manager.comment.dto.CreateCommentRequest;
import kanban_task_manager.comment.model.Comment;
import kanban_task_manager.comment.service.CommentService;

@RestController
@RequestMapping("/api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CreateCommentRequest request, Principal principal) {
        Comment comment = commentService.createComment(request, principal.getName());
        return ResponseEntity.ok(comment);
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<Comment>> getCommentsByCardId(@PathVariable String cardId) {
        List<Comment> comments = commentService.getCommentsByCardId(cardId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }
}