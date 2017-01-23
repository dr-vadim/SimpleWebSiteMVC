package ru.drv.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Auto Not Found")  // 404
public class AutoNotFoundException extends Exception {
    public AutoNotFoundException(String message) {
        super(message);
    }
}
