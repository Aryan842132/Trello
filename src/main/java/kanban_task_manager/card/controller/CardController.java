package kanban_task_manager.card.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kanban_task_manager.card.dto.CreateCardRequest;
import kanban_task_manager.card.dto.UpdateCardRequest;
import kanban_task_manager.card.model.Card;
import kanban_task_manager.card.service.CardService;

@RestController
@RequestMapping("/api/cards")
@CrossOrigin(origins = "*")
public class CardController {

    @Autowired
    private CardService cardService;

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody CreateCardRequest request) {
        Card card = cardService.createCard(request);
        return ResponseEntity.ok(card);
    }

    @GetMapping("/list/{listId}")
    public ResponseEntity<List<Card>> getCardsByListId(@PathVariable String listId) {
        List<Card> cards = cardService.getCardsByListId(listId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<Card>> getCardsByBoardId(@PathVariable String boardId) {
        List<Card> cards = cardService.getCardsByBoardId(boardId);
        return ResponseEntity.ok(cards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getCardById(@PathVariable String id) {
        Card card = cardService.getCardById(id);
        return ResponseEntity.ok(card);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> updateCard(@PathVariable String id, @RequestBody UpdateCardRequest request) {
        Card card = cardService.updateCard(id, request);
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCard(@PathVariable String id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }
}