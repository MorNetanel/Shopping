package com.example.shoppingproject.services;

import com.example.shoppingproject.repository.CustomerRepository;
import com.example.shoppingproject.repository.ProductRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerService  {


    private int id;

    private CustomerRepository customerRepository;

    private ProductRepository productRepository;

    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public int getId() {
        return id;
    }

    /**
     * Methods
     *
     * login
     *
     * get details update delete
     *
     * get one all by prices dates rating type, name
     * add to cart remove from  cart
     * purchase cart
     *
     */
}
