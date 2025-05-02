package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateAccountException extends RuntimeException {
    /**
     * Custom RuntimeException when attempting to register an account with a username that already exists.
     * Sends status code 409 (conflict).
     */
}
