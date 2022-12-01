package com.example.shoppingproject.repository;

import com.example.shoppingproject.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Modifying
    @Query(value = "delete from shopping.customers_products where customer_id = ?1", nativeQuery = true)
    public void deleteCustomerProductsHistory(int customersId);
	
	@Modifying
    @Query(value = "delete from shopping.customers_cart where customer_id = ?1", nativeQuery = true)
    public void deleteCustomerCartHistory(int customersId);
	
	@Query(value = "select * from shopping.customers_products where customer_id =?1 and products_id=?2" , nativeQuery = true)
    public Product getProdcutByCustomerIdAndProductId(int CustomerId, int ProductId);
}
