package kanban_task_manager.board.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kanban_task_manager.board.model.Board;

@Repository
public interface BoardRepository extends MongoRepository<Board, String> {
    List<Board> findByOwnerId(String ownerId);
    List<Board> findByMemberIdsContaining(String memberId);
}