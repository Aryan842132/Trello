package kanban_task_manager.list.dto;

public class UpdateListRequest {
    private String title;
    private Integer position;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
}