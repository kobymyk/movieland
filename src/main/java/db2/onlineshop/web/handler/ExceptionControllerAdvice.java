package db2.onlineshop.web.handler;

import db2.onlineshop.service.security.exception.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionControllerAdvice {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final String MESSAGE = "{\"error\" : %s}";

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
    @ExceptionHandler(AuthenticationException.class)
    public String handleAuthenticationException(Exception e) {
        log.warn("AuthenticationException", e);
        return formatError(e.getMessage());
    }

    private String formatError(String error) {
        String result = String.format(MESSAGE, error);
        log.trace("formatError:result={}", result);

        return result;
    }
}