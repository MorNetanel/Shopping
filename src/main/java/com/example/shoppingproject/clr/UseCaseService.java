package com.example.shoppingproject.clr;

import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.enums.Color;
import com.example.shoppingproject.enums.ProductType;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.repository.ProductRepository;
import com.example.shoppingproject.services.CompanyService;
import com.example.shoppingproject.services.CustomerService;
import com.example.shoppingproject.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
@Order(2)
public class UseCaseService implements CommandLineRunner {


    @Autowired
    private ApplicationContext applicationContext;


    @Override
    public void run(String... args) throws Exception {


        try {



            /*

             delete product
             
             */

            CompanyService companyService = applicationContext.getBean(CompanyService.class);
            companyService.login("1111", "2222");


            List<Product> products =
                    companyService
                            .getTopSalesProducts(3);
            products.forEach((product)->
                    System.out.println(product.getProductName() +
                            ":" + product.getSales()) );












        }catch (SystemException e){
            System.out.println(e.getMessage());
        }
    }
}
