package com.example.shoppingproject.services;

import com.example.shoppingproject.repository.UserRepository;
import com.example.shoppingproject.beans.Customer;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.beans.Users;
import com.example.shoppingproject.enums.ClientType;
import com.example.shoppingproject.exceptions.ErrMsg;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.repository.CustomerRepository;
import com.example.shoppingproject.repository.ProductRepository;

import java.sql.Date;
import java.util.ArrayList;
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
	public Customer login(String userName, String password) throws SystemException{
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
	public Customer updateCustomer(Customer customer) throws SystemException {
		// TODO updating a customers personal details
		
		String email = customer.getEmail();
		String password = customer.getPassword();
		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();
		Date date = new Date(System.currentTimeMillis());
		List<Users> users = userRepository.findAll();
		
		//TODO check if user name already exists
		for (Users users2 : users) {
			if(users2.getUserName().equals(customer.getUserName())) {
				throw new SystemException(ErrMsg.CUSTOMER_UPDATE_FAILURE);
			}
		}
		
		//TODO check if email, password, first name, last name and birthday are valid 
		if((email.length() < 8 || email.length() > 30) || 
				(password.length() < 4 || password.length() > 16) || 
					(firstName.length() < 3 || firstName.length() > 10)|| 
						(lastName.length() < 3 || lastName.length() > 10) ||
							customer.getBirthDate().before(date) ) {
			throw new SystemException(ErrMsg.CUSTOMER_UPDATE_FAILURE);
		}
		
		return customerRepository.save(customer);
	}

	@Override
	public boolean deleteCustomer() {
		// TODO Customer deletes himself from the application
		
		//delete from users table
		//remove from customer table
		//remove from customer_cart table 
		//remove from customers_products table
		
		productRepository.deleteCustomerProductsHistory(this.id);
		productRepository.deleteCustomerCartHistory(this.id);
		customerRepository.deleteById(this.id);
		userRepository.deleteById(this.id);
		return true;
	}

	@Override
	public Product getOneProduct(int id) throws SystemException {
		// TODO Get one product by a customer
		
		//check if id is valid
		//if valid check if product id matches customer id
		
		Product product = productRepository.findById(id).orElseThrow(()-> new SystemException(ErrMsg.ID_NOT_FOUND));
		if(product != null) {
			product = productRepository.getProductByCustomerIdAndProductId(this.id, id);
		}
		
		if(product != null) {
			return product;
		}
		else {
			throw new SystemException(ErrMsg.PRODUCT_EXIST);
		}

	}

	@Override
	public List<Product> getAllProducts() {
		// TODO get all customers products
		
		//get all the ids of products that belongs to customer
		//get all the products
		//run on both of them
		
		ArrayList<Integer> productsId = productRepository.getAllProductsIdByCustomersId(this.id);
		List<Product> products = productRepository.findAll();
		List<Product> returnProducts = new ArrayList<>();
		
		for (int i = 0; i < productsId.size(); i++) {
			for (int j = 0; j < products.size(); j++) {
				if(productsId.get(i) == products.get(j).getId()) {
					returnProducts.add(products.get(j));
				}
			}
		}
		
		return returnProducts;
	}

	@Override
	public List<Product> getProductsBetweenPrices(double minPrice, double maxPrice) {
		// TODO Get customers products between two prices
		List<Product> customersProducts = getAllProducts();
		List<Product> returnProducts = new ArrayList<>();
		
		for (int i = 0; i < customersProducts.size(); i++) {
			if(customersProducts.get(i).getPrice() > minPrice && 
					customersProducts.get(i).getPrice() < maxPrice) {
				returnProducts.add(customersProducts.get(i));
			}
		}
		return returnProducts;
	}

	@Override
	public List<Product> getAProductsBetweenDates(Date startDate, Date endDate) {
		// TODO Get all products between two dates
		List<Product> customersProducts = getAllProducts();
		List<Product> returnProducts = new ArrayList<>();
		
		for (int i = 0; i < customersProducts.size(); i++) {
			if((startDate.after(customersProducts.get(i).getPublishedDate())&&
					startDate.before(customersProducts.get(i).getExpiredDate())
					&&(customersProducts.get(i).getExpiredDate().after(endDate))
			   )) {
				returnProducts.add(customersProducts.get(i));
			}
		}
		return returnProducts;
	}

	@Override
	public List<Product> getTopRatingProducts(int numOfProducts) throws SystemException {
		// TODO Gets top rating products by numbers (top rating is 4-5 rating)
		
		List<Product> customersProducts = getAllProducts();
		List<Product> returnProducts = new ArrayList<>();
		
		int productCounter = numOfProducts;
		
		for (int i = 0; i < customersProducts.size(); i++) {
			if(customersProducts.get(i).getAverageRating() > 4) {
				returnProducts.add(customersProducts.get(i));
				productCounter--;
			}
			if(productCounter == 0)
				break;
		}
		if(productCounter == 0) {
			return returnProducts;
		}
		else throw new SystemException(ErrMsg.PRODUCT_EXIST);
	}

	@Override
	public List<Product> getProductsByName(String productName) throws SystemException {
		// TODO Get customer's products by the given name
		
		List<Product> customersProducts = getAllProducts();
		List<Product> returnProducts = new ArrayList<>();
		
		for (int i = 0; i < customersProducts.size(); i++) {
			if(customersProducts.get(i).getProductName().equalsIgnoreCase(productName)) {
				returnProducts.add(customersProducts.get(i));
				
			}
		}
		if(!returnProducts.isEmpty())
			return returnProducts;
		else throw new SystemException(ErrMsg.PRODUCT_EXIST);
		
	}

	@Override
	public List<Product> addProductToCart(Product product) throws SystemException {
		// TODO Add a product to the customer's shopping cart
		//add the product to the DB.
		//add to the cart list of the customer the product + update it
		
		
		Customer customer = customerRepository.findById(this.id).orElseThrow(()-> new SystemException(ErrMsg.CUSTOMER_EXIST));
		List<Product> totalProducts = productRepository.findAll();
		
		if(totalProducts.contains(product)) {
			productRepository.insertCustomerAndProduct(this.id, product.getId());
			customer.getCart().add(product);
			customerRepository.save(customer);
			return customer.getCart();
		}
		else throw new SystemException(ErrMsg.PRODUCT_EXIST);
	}

	@Override
	public List<Product> removeProductFromCart(Product product) throws SystemException {
		// TODO Getting rid of the product from the cart
		
		//removing it from the DB
		//removing it from the list
		
		Customer customer = customerRepository.findById(this.id).orElseThrow(()-> new SystemException(ErrMsg.CUSTOMER_EXIST));
		List<Product> totalProducts = productRepository.findAll();
		
		if(totalProducts.contains(product)) {
			productRepository.deleteCustomerAndProduct(this.id, product.getId());
			customer.getCart().remove(product);
			customerRepository.save(customer);
			return customer.getCart();
		}
		else throw new SystemException(ErrMsg.PRODUCT_EXIST);

		
	}

	@Override
	public boolean clearCart() {
		// TODO Clearing the total cart by customer's id
		productRepository.deleteCustomerCartHistory(this.id);
		return true;
	}

	@Override
	public boolean purchaseCart() throws SystemException {
		// TODO purchase products from the cart
		
		//get all the items by the id V
		//update customers_products V 
		//update customer itself (use method) V
		//remove 1 from quantity of product - if 0 - throw exception V
		//update the amount in the product DB V
		//delete purchase history of the user 
		
		List<Integer> productsId = productRepository.getAllProductsIdByCustomersIdAtCart(this.id);
		List<Product> products = productRepository.findAll();
		Customer customer = customerRepository.findById(this.id).orElseThrow(()-> new SystemException(ErrMsg.CUSTOMER_EXIST));
		
		for (int i = 0; i < productsId.size(); i++) {
			for (int j = 0; j < products.size(); j++) {
				if(productsId.get(i) == products.get(j).getId()) {
					Product product = productRepository.findById(productsId.get(i)).orElseThrow(()-> new SystemException(ErrMsg.PRODUCT_EXIST));
					if(product.getQuantity() < 0) {
						throw new SystemException(ErrMsg.PRODUCT_EXIST);
					}
					product.setQuantity(product.getQuantity()-1);
					productRepository.insertCustomerAndProductAtCustomerProducts(this.id, products.get(j).getId());
					customer.getProducts().add(products.get(j));
					
					customerRepository.save(customer);
					productRepository.save(product);
					
					clearCart();
				}
			}
		}
		return true;
	}

}
