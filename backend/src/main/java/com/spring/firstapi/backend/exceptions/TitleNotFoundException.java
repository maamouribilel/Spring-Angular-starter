package com.spring.firstapi.backend.exceptions;

public class TitleNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public TitleNotFoundException(String message) {
        super(message);
    }

}