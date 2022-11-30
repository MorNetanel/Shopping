package com.example.shoppingproject.exceptions;

public enum ErrMsg {

    ID_ALREADY_EXIST("Id already exist"),

    ID_NOT_FOUND("Id not found");







    private String description;

    ErrMsg(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
