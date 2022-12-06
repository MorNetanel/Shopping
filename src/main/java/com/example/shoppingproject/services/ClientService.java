package com.example.shoppingproject.services;

import com.example.shoppingproject.exceptions.SystemException;

public abstract class ClientService {

    abstract int login (String userName, String password ) throws SystemException;
}
