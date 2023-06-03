package ic.auth.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ic.auth.service.model.ErrorMessage;
import ic.auth.service.utils.Constants;
import ic.auth.service.utils.Utils;

import java.security.SignatureException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage userNotFoundHandler(UserNotFoundException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage(), (Utils.isEmptyString(exception.getResponseCode()) ? Constants.GENERAL_EXCEPTION : exception.getResponseCode()));
        return errorMessage;
    }

    @ExceptionHandler(MissingMandatoryInfo.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage mandatoryInfoMissingHandler(MissingMandatoryInfo exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage(), (Utils.isEmptyString(exception.getResponseCode()) ? Constants.GENERAL_EXCEPTION : exception.getResponseCode()));
        return errorMessage;
    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorMessage invalidTokenHandler(InvalidTokenException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage(), (Utils.isEmptyString(exception.getResponseCode()) ? Constants.GENERAL_EXCEPTION : exception.getResponseCode()));
        return errorMessage;
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage internalAuthenticationServiceExceptionHandler(InternalAuthenticationServiceException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, exception.getMessage(), Constants.GENERAL_EXCEPTION);
        return errorMessage;
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage signatureExceptionHandler(SignatureException exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, exception.getMessage(), Constants.GENERAL_EXCEPTION);
        return errorMessage;
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public ErrorMessage customExceptionHandler(CustomException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getHttpStatus(), exception.getMessage(), (Utils.isEmptyString(exception.getResponseCode()) ? Constants.GENERAL_EXCEPTION : exception.getResponseCode()));
        return errorMessage;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage genericExceptionHandler(Exception exception) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), Constants.GENERAL_EXCEPTION);
        return errorMessage;
    }
}
