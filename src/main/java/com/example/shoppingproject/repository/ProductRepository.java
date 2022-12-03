package com.example.shoppingproject.repository;

import com.example.shoppingproject.beans.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.shoppingproject.enums.ProductType;
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
     Product getProductByCustomerIdAndProductId(int CustomerId, int ProductId);
	
	
	@Query(value = "select products_id from shopping.customers_products where customer_id=?1", nativeQuery = true)
	 ArrayList<Integer> getAllProductsIdByCustomersId(int customerId);

    @Query(value = "select * from products where company_id = ?1", nativeQuery = true)
     List<Product>findByCompanyId(int company_id);

     Optional<Product> findByIdAndCompanyId(int id, int companyId);

    Optional<Product> findByProductName(String productName);

    @Query(value = "select * from products where published_date >= ?1 and published_date < ?2 ", nativeQuery = true)
    List<Product> findProductsBetweenPublishedDates(Date publishedDate, Date expiredDate);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);

    @Query(value = "select * from products where company_id = ?1 order by average_rating desc limit ?2", nativeQuery = true)
    List<Product> findTopRatingProductsByCompanyId( int companyId, int numOfProducts);

    @Query(value = "select * from products where company_id = ?1 order by average_rating asc limit ?2", nativeQuery = true)
    List<Product> findLowRatingProductsByCompanyId( int companyId, int numOfProducts);

    List<Product> findByProductType(ProductType productType);

    @Query(value = "select * from products where company_id = ?1 order by sales desc limit ?2", nativeQuery = true)
    List<Product> findTopSaleProductsByCompanyId(int companyId, int numOfProducts);

    @Modifying
    @Query(value = "insert into customers_cart values (?1, ?2);", nativeQuery = true)
     void insertCustomerAndProduct(int customer_id, int product_id);
    
    @Modifying
    @Query(value = "delete from shopping.customers_cart where customer_id = ?1 and cart_id = ?2", nativeQuery = true)
     void deleteCustomerAndProduct(int customerId, int product_id);
    
    @Query(value = "select cart_id from shopping.customers_cart where customer_id=?1", nativeQuery = true)
	 ArrayList<Integer> getAllProductsIdByCustomersIdAtCart(int customerId);
    
    @Modifying
    @Query(value = "insert into customers_products values (?1, ?2);", nativeQuery = true)
     void insertCustomerAndProductAtCustomerProducts(int customer_id, int product_id);




}
