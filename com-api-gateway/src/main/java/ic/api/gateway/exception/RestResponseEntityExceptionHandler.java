package ic.api.gateway.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import ic.api.gateway.model.ErrorMessage;
import ic.api.gateway.utils.Constants;
import ic.api.gateway.utils.Utils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

@ControllerAdvice
public class RestResponseEntityExceptionHandler implements WebExceptionHandler, Ordered {
    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        // Handle your custom exception here
        if (ex instanceof MissingMandatoryInfo) {
            MissingMandatoryInfo missingInfoException = (MissingMandatoryInfo) ex;
            exchange.getResponse().setStatusCode(missingInfoException.getHttpStatus());
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, missingInfoException.getMessage(), (Utils.isEmptyString(missingInfoException.getResponseCode()) ? Constants.GENERAL_EXCEPTION : missingInfoException.getResponseCode()));
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(customResponseToJsonBytes(errorMessage))));
        } else if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            exchange.getResponse().setStatusCode(customException.getHttpStatus());
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            ErrorMessage errorMessage = new ErrorMessage(HttpStatus.FORBIDDEN, customException.getMessage(), (Utils.isEmptyString(customException.getResponseCode()) ? Constants.GENERAL_EXCEPTION : customException.getResponseCode()));
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(customResponseToJsonBytes(errorMessage))));
        } else {
            // Generic Exception
            exchange.getResponse().setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
            exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
            ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), Constants.GENERAL_EXCEPTION);
            return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(customResponseToJsonBytes(errorMessage))));
        }
    }

    private byte[] customResponseToJsonBytes(ErrorMessage errorMessage) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsBytes(errorMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    @Override
    public int getOrder() {
        // Set a lower value to have higher precedence over other exception handlers
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
