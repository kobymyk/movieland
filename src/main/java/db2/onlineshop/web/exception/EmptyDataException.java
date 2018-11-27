package db2.onlineshop.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Data is empty")
public class EmptyDataException extends RuntimeException {

    public EmptyDataException(String message) {
        super(message);
    }
}
