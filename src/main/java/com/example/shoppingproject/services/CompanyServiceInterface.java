package com.example.shoppingproject.services;

import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.enums.ProductType;
import com.example.shoppingproject.exceptions.SystemException;

import java.sql.Date;
import java.util.List;

public interface CompanyServiceInterface {

    Company login (String userName, String password ) throws SystemException;



    Company getDetails (int id) throws SystemException;

    List<Product> getCompanyProducts();

    Product addProduct(Product product) throws SystemException;

    Product getOneProduct(int prodId) throws SystemException;

    Product getOneProductByName(String prodName);

    boolean deleteProduct (int prodId);

    Product updateProduct (Product product);

    List<Product> getProductsBetweenPublishedDates(Date startDate, Date endDate);

    List<Product> getProductsBetweenPrices (double minPrice, double maxPrice);

    List<Product> getTopRatingProducts(int numOfProducts);

    List<Product> getMinRatingProducts(int numOfProducts);

    List<Product> getProductsByType(ProductType productType);

    List<Product> getTopSalesProducts(int numOfProducts);




}
