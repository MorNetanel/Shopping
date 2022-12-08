package com.example.shoppingproject.controllers;

import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;


    /*
                                                        get details----------------WORKS!!!
                                                        get company products-------WORKS!!!
                                                        add product----------------WORKS!!!
                                                        get one product------------WORKS!!!
                                                        get one product by name----WORKS!!!
                                                        delete product
                                                        update product/////////////WORKS!!!
                                                        get products between published dates//////WORKS!!!
                                                        get products between prices///WORKS!!!
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



    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
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



    @GetMapping("/by_dates")
    public List<Product> getProductsBetweenPublishedDates(@RequestParam Date startDate, @RequestParam Date endDate){
        return companyService.getProductsBetweenPublishedDates(startDate, endDate);
    }

    @GetMapping("/prices")
    public List<Product> getProductsBetweenPrices(@RequestParam double minPrice, @RequestParam double maxPrice) throws SystemException {
        return companyService.getProductsBetweenPrices(minPrice, maxPrice);
    }




}
