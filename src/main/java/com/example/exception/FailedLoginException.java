package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class FailedLoginException extends RuntimeException {
    /**
     * Custom RuntimeException when a login request fails.
     * Sends status code 401 (unauthorized).
     */
}
