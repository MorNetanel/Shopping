package com.example.shoppingproject.repository;

import com.example.shoppingproject.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	Customer findByUserNameAndPassword(String username, String password);
}
