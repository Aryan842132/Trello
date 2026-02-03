package kanban_task_manager.auth.dto;

public class AuthResponse {
    private String token;
    private String refreshToken;
    private String message;
    private Object user;

    public AuthResponse() {
    }

    public AuthResponse(String token, String refreshToken, String message, Object user) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.message = message;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }
}