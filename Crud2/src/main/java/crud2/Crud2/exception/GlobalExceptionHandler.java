package crud2.Crud2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Custom error response structure
    static class ErrorResponse {
        private String message;
        private LocalDateTime timestamp;
        private String path;

        public ErrorResponse(String message, LocalDateTime timestamp, String path) {
            this.message = message;
            this.timestamp = timestamp;
            this.path = path;
        }

        // Getters
        public String getMessage() {
            return message;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        public String getPath() {
            return path;
        }
    }

    // Handle ResourceNotFoundException
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex, WebRequest request) {
//        ErrorResponse error = new ErrorResponse(
//                ex.getMessage(),
//                LocalDateTime.now(),
//                request.getDescription(false)
//                        .replace("uri=", ""));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Handle all other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {

        ErrorResponse error = new ErrorResponse(
                "Something went wrong!",
                LocalDateTime.now(),
                request.getDescription(false)
                        .replace("uri=", ""));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
