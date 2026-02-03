package kanban_task_manager.card.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kanban_task_manager.card.model.Card;

@Repository
public interface CardRepository extends MongoRepository<Card, String> {
    List<Card> findByListIdOrderByPositionAsc(String listId);
    List<Card> findByBoardId(String boardId);
    List<Card> findByAssignedUserId(String assignedUserId);
}