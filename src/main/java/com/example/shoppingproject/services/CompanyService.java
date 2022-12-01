package com.example.shoppingproject.services;

import com.example.shoppingproject.repository.CompanyRepository;
import com.example.shoppingproject.repository.ProductRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CompanyService   {

    private int id;
    private CompanyRepository companyRepository;
    private ProductRepository productRepository;

    public CompanyService(CompanyRepository companyRepository, ProductRepository productRepository) {
        this.companyRepository = companyRepository;
        this.productRepository = productRepository;
    }

    public int getId() {
        return id;
    }

    /**
     * METHODS
     *
     * login
     *
     * get details
     *
     * product:
     * get company products add product get one product delete product update product
     * get between published dates, get between prices get top/min average rating get by name
     * get by type get top sales
     */


}
