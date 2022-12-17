package com.example.shoppingproject.security;

import com.example.shoppingproject.repository.UserRepository;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Repository
public class ApplicationUserDaoService implements ApplicationUserDao{


    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;





    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username, String password) {
        return userRepository.findClientTypeByUserNameAndPassword(username, password);
    }



}
