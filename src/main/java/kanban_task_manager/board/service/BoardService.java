package kanban_task_manager.board.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kanban_task_manager.auth.model.User;
import kanban_task_manager.auth.repository.UserRepository;
import kanban_task_manager.board.dto.CreateBoardRequest;
import kanban_task_manager.board.dto.UpdateBoardRequest;
import kanban_task_manager.board.model.Board;
import kanban_task_manager.board.repository.BoardRepository;
import kanban_task_manager.common.exception.ResourceNotFoundException;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board createBoard(CreateBoardRequest request, String userId) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setDescription(request.getDescription());
        board.setOwnerId(userId);
        
        // Get user IDs for members
        List<String> memberIds = new ArrayList<>();
        memberIds.add(userId); // Add owner as member
        
        if (request.getMemberEmails() != null) {
            for (String email : request.getMemberEmails()) {
                Optional<User> user = userRepository.findByEmail(email);
                if (user.isPresent()) {
                    memberIds.add(user.get().getUserId());
                }
            }
        }
        
        board.setMemberIds(memberIds);
        board.setCreatedAt(LocalDateTime.now());
        board.setUpdatedAt(LocalDateTime.now());

        return boardRepository.save(board);
    }

    public List<Board> getUserBoards(String userId) {
        List<Board> boardsByOwner = boardRepository.findByOwnerId(userId);
        List<Board> boardsForMember = boardRepository.findByMemberIdsContaining(userId);
        
        // Combine and remove duplicates
        List<Board> allBoards = new ArrayList<>(boardsByOwner);
        for (Board board : boardsForMember) {
            if (!allBoards.contains(board)) {
                allBoards.add(board);
            }
        }
        
        return allBoards;
    }

    public Board getBoardById(String boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found with id: " + boardId));
    }

    public Board updateBoard(String boardId, UpdateBoardRequest request, String userId) {
        Board board = getBoardById(boardId);
        
        // Check if user is owner
        if (!board.getOwnerId().equals(userId)) {
            throw new RuntimeException("You don't have permission to update this board");
        }
        
        board.setTitle(request.getTitle());
        board.setDescription(request.getDescription());
        board.setUpdatedAt(LocalDateTime.now());

        return boardRepository.save(board);
    }

    public void deleteBoard(String boardId, String userId) {
        Board board = getBoardById(boardId);
        
        // Check if user is owner
        if (!board.getOwnerId().equals(userId)) {
            throw new RuntimeException("You don't have permission to delete this board");
        }
        
        boardRepository.deleteById(boardId);
    }
}