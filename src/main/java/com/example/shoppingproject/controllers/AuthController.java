package com.example.shoppingproject.controllers;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.shoppingproject.exceptions.SystemException;
import com.example.shoppingproject.loginManager.LoginManager;
import com.example.shoppingproject.services.ClientService;
import com.example.shoppingproject.services.CompanyService;
import com.example.shoppingproject.services.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(value = "/auth")
@AllArgsConstructor
@CrossOrigin
public class AuthController {

    private LoginManager loginManager;



    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestParam String user, @RequestParam String password)  {

        ClientService clientService = null;
        try {

            clientService = loginManager.login(user, password);

            int id = 0;
             if (clientService instanceof CompanyService)
                id = ((CompanyService) clientService).getId();
            else if (clientService instanceof CustomerService)
                id = ((CustomerService) clientService).getId();
            String token = createToken(user, id);

//            sessions.put(token, new ClientSession(clientService, System.currentTimeMillis()));


            return ResponseEntity.ok(token);


        } catch (SystemException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }

    }


    private String createToken(String userName, int id){
        String token = JWT.create()
                .withIssuer("Shopping")
                .withIssuedAt(new Date())
                .withClaim("id", id)
                .withClaim("user_name", userName)
                .sign(Algorithm.HMAC256("java-fullstack-developers"));
        return token;
    }










}
