package com.example.shoppingproject.repository;

import com.example.shoppingproject.beans.Users;
import com.example.shoppingproject.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Integer> {


    @Query(value = "select client_type from users where user_name = ?1 and password = ?2", nativeQuery = true)
    ClientType findClientTypeByUserNameAndPassword(String userName, String password);
}
