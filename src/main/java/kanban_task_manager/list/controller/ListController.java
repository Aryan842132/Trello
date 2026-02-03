package kanban_task_manager.list.controller;

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

import kanban_task_manager.list.dto.CreateListRequest;
import kanban_task_manager.list.dto.UpdateListRequest;
import kanban_task_manager.list.model.ListEntity;
import kanban_task_manager.list.service.ListService;

@RestController
@RequestMapping("/api/lists")
@CrossOrigin(origins = "*")
public class ListController {

    @Autowired
    private ListService listService;

    @PostMapping
    public ResponseEntity<ListEntity> createList(@RequestBody CreateListRequest request) {
        ListEntity list = listService.createList(request);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<ListEntity>> getListsByBoardId(@PathVariable String boardId) {
        List<ListEntity> lists = listService.getListsByBoardId(boardId);
        return ResponseEntity.ok(lists);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListEntity> getListById(@PathVariable String id) {
        ListEntity list = listService.getListById(id);
        return ResponseEntity.ok(list);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ListEntity> updateList(@PathVariable String id, @RequestBody UpdateListRequest request) {
        ListEntity list = listService.updateList(id, request);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteList(@PathVariable String id) {
        listService.deleteList(id);
        return ResponseEntity.noContent().build();
    }
}