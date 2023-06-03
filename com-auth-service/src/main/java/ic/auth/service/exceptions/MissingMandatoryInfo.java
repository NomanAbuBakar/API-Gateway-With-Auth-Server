package ic.auth.service.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
public class MissingMandatoryInfo extends RuntimeException {
    private String errorCode;
    private int status;
    private String responseCode;
    @Builder.Default
    HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public MissingMandatoryInfo(String message) {
        super(message);
    }

    public MissingMandatoryInfo(String message, int status) {
        super(message);
        this.status = status;
    }

    public MissingMandatoryInfo(String message, String errorCode, int status, HttpStatus httpStatus){
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.httpStatus = httpStatus;
    }

    public MissingMandatoryInfo(String message, String errorCode, int status, HttpStatus httpStatus, String responseCode){
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.httpStatus = httpStatus;
        this.responseCode = responseCode;
    }
}
