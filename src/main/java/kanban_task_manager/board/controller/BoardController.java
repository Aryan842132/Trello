package kanban_task_manager.board.controller;

import java.security.Principal;
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

import kanban_task_manager.board.dto.CreateBoardRequest;
import kanban_task_manager.board.dto.UpdateBoardRequest;
import kanban_task_manager.board.model.Board;
import kanban_task_manager.board.service.BoardService;

@RestController
@RequestMapping("/api/boards")
@CrossOrigin(origins = "*")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody CreateBoardRequest request, Principal principal) {
        Board board = boardService.createBoard(request, principal.getName());
        return ResponseEntity.ok(board);
    }

    @GetMapping
    public ResponseEntity<List<Board>> getUserBoards(Principal principal) {
        List<Board> boards = boardService.getUserBoards(principal.getName());
        return ResponseEntity.ok(boards);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable String id) {
        Board board = boardService.getBoardById(id);
        return ResponseEntity.ok(board);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable String id, @RequestBody UpdateBoardRequest request, Principal principal) {
        Board board = boardService.updateBoard(id, request, principal.getName());
        return ResponseEntity.ok(board);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable String id, Principal principal) {
        boardService.deleteBoard(id, principal.getName());
        return ResponseEntity.noContent().build();
    }
}