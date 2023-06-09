package ic.auth.service.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomException extends RuntimeException {
    private String errorCode;
    private int status;
    private String responseCode;
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public CustomException(String message, String errorCode, int status) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
    }

    public CustomException(String message, String errorCode, int status, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public CustomException(String message, String errorCode, int status, HttpStatus httpStatus, String responseCode) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
    }
}
