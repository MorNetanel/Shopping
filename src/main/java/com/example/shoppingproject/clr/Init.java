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
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class Init implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GuestService guestService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CustomerService customerService;

    @Override
    public void run(String... args) {

        try {


//        Company company = Company.builder()
//                .email("c@cc")
//                .password("1111")
//                .userName("cccs")
//                .build();


//        Product product = Product.builder()
//                .color(Color.BLUE)
//                .price(15.5)
//                .description("ahla product")
//                .quantity(4)
//                .productType(ProductType.BOOKS)
//                .ExpiredDate(Date.valueOf("2023-10-10"))
//                .company(companyRepository.getReferenceById(1))
//                .build();


//        if (!companyRepository.findAll().stream().anyMatch(company1 -> company.getEmail().equalsIgnoreCase(company1.getEmail()))) {
//
//            companyRepository.save(company);
//        }
//
//        productRepository.save(product);

//        System.out.println(userRepository.findClientTypeByUserNameAndPassword("1111", "1111"));

            companyService.login("1111", "1111");

            Company company =companyService.getDetails(2);
            System.out.println(company);

            companyRepository.findAll().forEach(System.out::println);


        }catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("hey nati");
        }
    }
}
