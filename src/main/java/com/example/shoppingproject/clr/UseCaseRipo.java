package com.example.shoppingproject.clr;

import com.example.shoppingproject.repository.CompanyRepository;
import com.example.shoppingproject.repository.CustomerRepository;
import com.example.shoppingproject.repository.ProductRepository;
import com.example.shoppingproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class UseCaseRipo implements CommandLineRunner {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

    }
}
