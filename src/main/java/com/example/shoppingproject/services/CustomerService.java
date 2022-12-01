package com.example.shoppingproject.services;

import com.example.shoppingproject.repository.UserRepository;
import com.example.shoppingproject.beans.Customer;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.enums.ClientType;
import com.example.shoppingproject.exceptions.ErrMsg;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.repository.CustomerRepository;
import com.example.shoppingproject.repository.ProductRepository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CustomerService implements CustomerServiceInterface {
	
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


    private int id = -1;

    private CustomerRepository customerRepository;

    private ProductRepository productRepository;

    private UserRepository userRepository;

    public CustomerService(CustomerRepository customerRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public int getId() {
        return id;
    }

	@Override
	public Customer login(String userName, String password) throws SystemException {
		// TODO Login method by user name and password
		if(userRepository.findClientTypeByUserNameAndPassword(userName, password) == ClientType.CUSTOMER) {
			Customer customer = customerRepository.findByUserNameAndPassword(userName, password);
			this.id = customer.getId();
			return customer;
		}
		 else throw new SystemException(ErrMsg.ID_NOT_FOUND);
	}

	@Override
	public Customer getDetails() throws SystemException{
		// TODO get details of the customer
		return customerRepository.findById(id).orElseThrow(()->new SystemException(ErrMsg.ID_NOT_FOUND));
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteCustomer(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product getOneProduct(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsBetweenPrices(double minPrice, double maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getAProductsBetweenDates(Date startDate, Date endDate) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getTopRatingProducts(int numOfProducts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> addProductToCart(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> removeProductFromCart(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean clearCart() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean purchaseCart() {
		// TODO Auto-generated method stub
		return false;
	}

}
