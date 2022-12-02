package com.example.shoppingproject.clr;

import com.example.shoppingproject.services.CompanyService;
import com.example.shoppingproject.services.CustomerService;
import com.example.shoppingproject.services.GuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class UseCaseService implements CommandLineRunner {


    @Autowired
    private GuestService guestService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CustomerService customerService;


    @Override
    public void run(String... args) throws Exception {

    }
}
