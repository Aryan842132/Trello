package kanban_task_manager.list.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kanban_task_manager.list.model.ListEntity;

@Repository
public interface ListRepository extends MongoRepository<ListEntity, String> {
    List<ListEntity> findByBoardIdOrderByPositionAsc(String boardId);
}