package com.example.shoppingproject.services;

import com.example.shoppingproject.beans.Customer;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.exceptions.SystemException;

import java.sql.Date;
import java.util.List;

public interface CustomerServiceInterface {

    Customer login(String userName, String password) throws SystemException;

    Customer updateCustomer(Customer customer) throws SystemException;

	boolean deleteCustomer();

    Product getOneProduct (int id);

    List<Product> getAllProducts();

    List<Product> getProductsBetweenPrices(double minPrice, double maxPrice);

    List<Product> getAProductsBetweenDates(Date startDate, Date endDate);

    List<Product> getTopRatingProducts(int numOfProducts);

    List<Product> getProductsByName(String productName);

    List<Product> addProductToCart (Product product);

    List<Product> removeProductFromCart (Product product);

    boolean clearCart();

    boolean purchaseCart();

	Customer getDetails() throws SystemException;






}
