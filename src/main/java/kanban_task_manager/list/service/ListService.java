package kanban_task_manager.list.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanban_task_manager.card.repository.CardRepository;
import kanban_task_manager.common.exception.ResourceNotFoundException;
import kanban_task_manager.list.dto.CreateListRequest;
import kanban_task_manager.list.dto.UpdateListRequest;
import kanban_task_manager.list.model.ListEntity;
import kanban_task_manager.list.repository.ListRepository;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private CardRepository cardRepository;

    public ListEntity createList(CreateListRequest request) {
        ListEntity list = new ListEntity();
        list.setTitle(request.getTitle());
        list.setBoardId(request.getBoardId());
        list.setPosition(request.getPosition());
        list.setCreatedAt(LocalDateTime.now());
        list.setUpdatedAt(LocalDateTime.now());

        return listRepository.save(list);
    }

    public List<ListEntity> getListsByBoardId(String boardId) {
        return listRepository.findByBoardIdOrderByPositionAsc(boardId);
    }

    public ListEntity getListById(String listId) {
        return listRepository.findById(listId)
                .orElseThrow(() -> new ResourceNotFoundException("List not found with id: " + listId));
    }

    public ListEntity updateList(String listId, UpdateListRequest request) {
        ListEntity list = getListById(listId);
        
        if (request.getTitle() != null) {
            list.setTitle(request.getTitle());
        }
        if (request.getPosition() != null) {
            list.setPosition(request.getPosition());
        }
        list.setUpdatedAt(LocalDateTime.now());

        return listRepository.save(list);
    }

    public void deleteList(String listId) {
        // First delete all cards in this list
        // Note: This would typically be handled by a card service method
        listRepository.deleteById(listId);
    }
}