package com.example.shoppingproject.controllers;

import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    /*
    get details
    get company products
    add product
    get one product
    get one product by name
    delete product
    update product
    get products between published dates
    get products between prices
    get top rating products
    get min rating products
    get products by type
    get top sales products
    */

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) throws SystemException {
        return companyService.addProduct(product);
    }



}
