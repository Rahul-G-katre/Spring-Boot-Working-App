package crud2.Crud2.exception;

import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, String path, LocalDateTime timestamp) {
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
    }

    // Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
