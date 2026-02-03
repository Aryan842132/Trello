package kanban_task_manager.comment.dto;

public class CreateCommentRequest {
    private String content;
    private String cardId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}