package ic.auth.service.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {
    private HttpStatus status;
    private String responseCode;
    private String message;

    public ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ErrorMessage(HttpStatus status, String message, String responseCode) {
        this.status = status;
        this.message = message;
        this.responseCode = responseCode;
    }
}