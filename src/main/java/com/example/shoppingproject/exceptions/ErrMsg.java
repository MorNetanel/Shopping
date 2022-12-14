package com.example.shoppingproject.exceptions;

public enum ErrMsg {

    ID_ALREADY_EXIST("Id already exist"),

    ID_NOT_FOUND("Id not found"),

    COMPANY_EXIST("Company Already exist"),

    CUSTOMER_EXIST("Customer already exist"),

    COMPANY_LOGIN_FAILURE("Failed to login"),

    CUSTOMER_UPDATE_FAILURE("Failed to update"),

    PRODUCT_EXIST("Product not found"),

    ACTION_FAILED("Action failed")




    ;

    private String description;

    ErrMsg(String description) {
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
