package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidMessageException extends RuntimeException {
    /**
     * Custom RuntimeException when posting a new message fails.
     * Sends status code 400 (bad request / client error).
     */
}
