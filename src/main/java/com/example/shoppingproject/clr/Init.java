package com.example.shoppingproject.clr;


import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.enums.Color;
import com.example.shoppingproject.enums.ProductType;
import com.example.shoppingproject.repository.CompanyRepository;
import com.example.shoppingproject.repository.CustomerRepository;
import com.example.shoppingproject.repository.ProductRepository;
import com.example.shoppingproject.repository.UserRepository;
import com.example.shoppingproject.services.CompanyService;
import com.example.shoppingproject.services.CustomerService;
import com.example.shoppingproject.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Order(1)
public class Init implements CommandLineRunner {







    @Override
    public void run(String... args) {

        try {












        }catch (Exception e){
            System.out.println(e.getMessage());

           

        }
    }
}
