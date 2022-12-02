package com.example.shoppingproject.repository;

import com.example.shoppingproject.beans.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Modifying
    @Query(value = "delete from shopping.customers_products where customer_id = ?1", nativeQuery = true)
     void deleteCustomerProductsHistory(int customersId);
	
	@Modifying
    @Query(value = "delete from shopping.customers_cart where customer_id = ?1", nativeQuery = true)
     void deleteCustomerCartHistory(int customersId);
	
	@Query(value = "select * from shopping.customers_products where customer_id =?1 and products_id=?2" , nativeQuery = true)
     Product getProdcutByCustomerIdAndProductId(int CustomerId, int ProductId);
	
	
	@Query(value = "select products_id from shopping.customers_products where customer_id=?1", nativeQuery = true)
	 ArrayList<Integer> getAllProductsIdByCustomersId(int customerId);

     List<Product>findByCompanyId();

     Optional<Product> findByIdAndComapnyId(int id, int comapnyId);

    Optional<Product> findByProductNameAndComapnyId(String productName,  int comapnyId);



}
