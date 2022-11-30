package com.example.shoppingproject.services;

import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Customer;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.exceptions.ErrMsg;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.repository.CompanyRepository;
import com.example.shoppingproject.repository.CustomerRepository;
import com.example.shoppingproject.repository.ProductRepository;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GuestService {


    private CompanyRepository companyRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;


    public GuestService(CompanyRepository companyRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.companyRepository = companyRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }



    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    public Company registerForCompany(Company company) throws SystemException {
        List<Company> companies = companyRepository.findAll();
            //find if company with same name | email | user name exist in db.
        for (Company c:companies) {
            if (company.getCompanyName().equalsIgnoreCase(c.getCompanyName())
            || company.getEmail().equalsIgnoreCase(c.getEmail())
            || company.getUserName().equalsIgnoreCase(c.getUserName()))
                throw  new SystemException(ErrMsg.COMPANY_EXIST);
        }
        return companyRepository.save(company);
    }

    public Customer registerForCustomer(Customer customer) throws SystemException {
        List<Customer> customers = customerRepository.findAll();
            //find if customer with same email, user Name exist
        if (customers.stream()
                .anyMatch(customer1 ->
                        customer1.getEmail().equalsIgnoreCase(customer.getEmail()) ||
                customer1.getUserName().equalsIgnoreCase(customer.getUserName())))
            throw new SystemException(ErrMsg.CUSTOMER_EXIST);

        return customerRepository.save(customer);

    }
}
