package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidAccountException extends RuntimeException {
    /**
     * Custom RuntimeException when registering a new account fails (and DuplicateAccountException isn't thrown).
     * Sends status code 400 (bad request / client error).
     */
}
