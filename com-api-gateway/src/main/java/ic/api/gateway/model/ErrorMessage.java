package ic.api.gateway.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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