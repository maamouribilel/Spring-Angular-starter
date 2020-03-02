package com.spring.firstapi.backend.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(TitleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails titleNotFound(ArticleNotFoundException ex){
            return new CustomErrorDetails(new Date(), "From  @RestControllerAdvice NOT FOUND", ex.getMessage());
        }
}