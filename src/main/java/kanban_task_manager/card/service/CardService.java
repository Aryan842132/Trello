package kanban_task_manager.card.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanban_task_manager.card.dto.CreateCardRequest;
import kanban_task_manager.card.dto.UpdateCardRequest;
import kanban_task_manager.card.model.Card;
import kanban_task_manager.card.repository.CardRepository;
import kanban_task_manager.common.exception.ResourceNotFoundException;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public Card createCard(CreateCardRequest request) {
        Card card = new Card();
        card.setTitle(request.getTitle());
        card.setDescription(request.getDescription());
        card.setListId(request.getListId());
        card.setBoardId(request.getBoardId());
        card.setAssignedUserId(request.getAssignedUserId());
        card.setPosition(request.getPosition());
        card.setPriority(request.getPriority());
        card.setDueDate(request.getDueDate());
        card.setCreatedAt(LocalDateTime.now());
        card.setUpdatedAt(LocalDateTime.now());

        return cardRepository.save(card);
    }

    public List<Card> getCardsByListId(String listId) {
        return cardRepository.findByListIdOrderByPositionAsc(listId);
    }

    public List<Card> getCardsByBoardId(String boardId) {
        return cardRepository.findByBoardId(boardId);
    }

    public Card getCardById(String cardId) {
        return cardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Card not found with id: " + cardId));
    }

    public Card updateCard(String cardId, UpdateCardRequest request) {
        Card card = getCardById(cardId);

        if (request.getTitle() != null) {
            card.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            card.setDescription(request.getDescription());
        }
        if (request.getListId() != null) {
            card.setListId(request.getListId());
        }
        if (request.getAssignedUserId() != null) {
            card.setAssignedUserId(request.getAssignedUserId());
        }
        if (request.getPosition() != null) {
            card.setPosition(request.getPosition());
        }
        if (request.getPriority() != null) {
            card.setPriority(request.getPriority());
        }
        if (request.getDueDate() != null) {
            card.setDueDate(request.getDueDate());
        }
        card.setUpdatedAt(LocalDateTime.now());

        return cardRepository.save(card);
    }

    public void deleteCard(String cardId) {
        cardRepository.deleteById(cardId);
    }
}