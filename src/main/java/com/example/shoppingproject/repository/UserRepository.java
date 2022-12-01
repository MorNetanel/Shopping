package com.example.shoppingproject.repository;

import com.example.shoppingproject.beans.Users;
import com.example.shoppingproject.enums.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Integer> {


    ClientType findClientTypeByUserNameAndPassword(String userName, String password);
}
