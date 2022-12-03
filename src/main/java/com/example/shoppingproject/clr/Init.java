package com.example.shoppingproject.clr;


import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Customer;
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
import com.example.shoppingproject.util.Art;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
@Order(1)
public class Init implements CommandLineRunner {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;








    @Override
    public void run(String... args) {
//        System.out.println(Art.init);

        try {

//            Company company = Company.builder()
//                    .email("2@222")
//                    .userName("111111")
//                    .companyName("RC")
//                    .password("22222")
//
//                    .build();

//            companyRepository.save(company);


//            Product product = Product.builder()
//                    .ExpiredDate(Date.valueOf("2023-01-01"))
//                    .color(Color.BLACK)
//                    .quantity(4)
//                    .productName("The cola")
//                    .description("glass bottle")
//                    .image("(____)")
//                    .company(companyRepository.findById(1).orElseThrow())
//                    .build();
//
//            productRepository.save(product);




















        }catch (Exception e){
            System.out.println(e.getMessage());

           

        }
    }
}
