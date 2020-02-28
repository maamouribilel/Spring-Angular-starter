package com.spring.firstapi.backend.exceptions;

public class ArticleExistsException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ArticleExistsException(String message) {
        super(message);
    }

}