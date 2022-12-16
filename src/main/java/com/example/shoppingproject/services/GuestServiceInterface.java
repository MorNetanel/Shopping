package com.example.shoppingproject.services;

import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Customer;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.exceptions.SystemException;

import java.util.List;

public interface GuestServiceInterface {

    List<Product> getAllProducts();



    Company registerForCompany(Company company) throws SystemException;

    Customer registerForCustomer (Customer customer) throws SystemException;
}
