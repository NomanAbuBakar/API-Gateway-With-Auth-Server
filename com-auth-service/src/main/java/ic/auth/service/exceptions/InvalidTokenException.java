package ic.auth.service.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class InvalidTokenException extends RuntimeException {
    private String errorCode;
    private int status;
    private String responseCode;
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public InvalidTokenException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public InvalidTokenException(String message, String errorCode, int status, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public InvalidTokenException(String message, String errorCode, int status, HttpStatus httpStatus,
            String responseCode) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
    }
}
