package com.example.shoppingproject.exceptions;

public class SystemException extends Exception{

    public SystemException(ErrMsg errMsg) {
        super(errMsg.getDescription());
    }
}
