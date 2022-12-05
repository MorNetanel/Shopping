package com.example.shoppingproject.controllers;

import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    //work
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) throws SystemException {
        return companyService.addProduct(product);
    }

    //work
    @GetMapping("details")
    public Company getDetails() throws SystemException {
        return companyService.getDetails();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteProduct(@PathVariable int id){
        return companyService.deleteProduct(id);
    }


    //not retrieve product to postman
    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Product updateProduct(@RequestBody Product product) throws SystemException {
        return companyService.updateProduct(product);
    }



    //work
    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) throws SystemException {
        return companyService.getOneProduct(id);
    }

    //work
    @GetMapping("/name")
    public Product getProductByName(@RequestParam String name) throws SystemException {

        return companyService.getOneProductByName(name);
    }

    //work, need to be checked with other company products(to see if filtered)
    @GetMapping
    public List<Product> getAllProducts(){
        return companyService.getCompanyProducts();
    }



}
