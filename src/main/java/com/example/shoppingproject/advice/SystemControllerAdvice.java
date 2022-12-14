package com.example.shoppingproject.advice;


import com.example.shoppingproject.exceptions.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class SystemControllerAdvice {

    @ExceptionHandler(value = {SystemException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetails exceptionHandler(Exception e){
        return new ErrorDetails("ShoppingException" , e.getMessage());
    }
}
