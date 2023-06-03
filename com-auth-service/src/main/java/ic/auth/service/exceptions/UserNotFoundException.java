package ic.auth.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserNotFoundException extends RuntimeException {
    private String errorCode;
    private int status;
    @Builder.Default
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String responseCode;

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, String errorCode, int status, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public UserNotFoundException(String message, String errorCode, int status, HttpStatus httpStatus,
            String responseCode) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
    }
}
