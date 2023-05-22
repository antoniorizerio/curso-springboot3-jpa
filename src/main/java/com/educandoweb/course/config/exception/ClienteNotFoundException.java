package com.educandoweb.course.config.exception;

public class ClienteNotFoundException extends RuntimeException {

    public ClienteNotFoundException(Long id) {
        super(String.format("User with id %d not found.", id));
    }
}
