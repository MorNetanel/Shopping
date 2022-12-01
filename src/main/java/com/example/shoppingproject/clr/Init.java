package com.example.shoppingproject.clr;


import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.enums.Color;
import com.example.shoppingproject.enums.ProductType;
import com.example.shoppingproject.repository.CompanyRepository;
import com.example.shoppingproject.repository.CustomerRepository;
import com.example.shoppingproject.repository.ProductRepository;
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

    @Override
    public void run(String... args) throws Exception {


        Company company = Company.builder()
                .email("c@cccc")
                .password("1111")
                .userName("ccccc")
                .build();




        companyRepository.save(company);

        Product product = Product.builder()
                .color(Color.BLUE)
                .price(15.5)
                .description("ahla product")
                .quantity(4)
                .productType(ProductType.BOOKS)
                .ExpiredDate(Date.valueOf("2023-10-10"))
                .company(companyRepository.getReferenceById(1))
                .build();

        productRepository.save(product);





        companyRepository.findAll().forEach(System.out::println);




    }
}
