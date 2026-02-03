package kanban_task_manager.comment.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanban_task_manager.comment.dto.CreateCommentRequest;
import kanban_task_manager.comment.model.Comment;
import kanban_task_manager.comment.repository.CommentRepository;
import kanban_task_manager.common.exception.ResourceNotFoundException;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public Comment createComment(CreateCommentRequest request, String userId) {
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setCardId(request.getCardId());
        comment.setUserId(userId);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByCardId(String cardId) {
        return commentRepository.findByCardIdOrderByCreatedAtAsc(cardId);
    }

    public Comment getCommentById(String commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + commentId));
    }

    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }
}