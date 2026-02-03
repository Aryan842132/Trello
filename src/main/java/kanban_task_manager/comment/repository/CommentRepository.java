package kanban_task_manager.comment.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kanban_task_manager.comment.model.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {
    List<Comment> findByCardIdOrderByCreatedAtAsc(String cardId);
}