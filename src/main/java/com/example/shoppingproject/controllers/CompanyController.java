package com.example.shoppingproject.controllers;

import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.enums.ProductType;
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






    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product addProduct(@RequestBody Product product) throws SystemException {
        return companyService.addProduct(product);
    }


    @GetMapping("details")
    public Company getDetails() throws SystemException {
        return companyService.getDetails();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) throws SystemException {
         companyService.deleteProduct(id);
    }



    @PutMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Product updateProduct(@RequestBody Product product) throws SystemException {
        return companyService.updateProduct(product);
    }




    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) throws SystemException {
        return companyService.getOneProduct(id);
    }




    @GetMapping("/name")
    public Product getProductByName(@RequestParam String name) throws SystemException {

        return companyService.getOneProductByName(name);
    }



    
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

    @GetMapping("/top")
    public List<Product> getTopRatingProducts(@RequestParam int num){
        return companyService.getTopRatingProducts(num);
    }

    @GetMapping("/min")
    public List<Product> getMinRatingProducts(@RequestParam int num){
        return companyService.getMinRatingProducts(num);
    }

    @GetMapping("/type")
    public List<Product> getProductsByType(@RequestParam ProductType type){
        return companyService.getProductsByType(type);
    }

    @GetMapping("/topsales")
    public List<Product>getTopSalesProducts(@RequestParam int num){
        return companyService.getTopSalesProducts(num);
    }








}
