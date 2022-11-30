package com.example.shoppingproject.exceptions;

public enum ErrMsg {

    ID_ALREADY_EXIST("Id already exist"),

    ID_NOT_FOUND("Id not found"),

    COMPANY_EXIST("Company Already exist"),

    CUSTOMER_EXIST("Customer already exist")






    ;

    private String description;

    ErrMsg(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
