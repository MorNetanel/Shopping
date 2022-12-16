package com.example.shoppingproject.controllers;


import com.example.shoppingproject.beans.Company;
import com.example.shoppingproject.beans.Product;
import com.example.shoppingproject.services.GuestService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/guest")
@AllArgsConstructor
@CrossOrigin
public class GuestController {

    private final GuestService guestService;


    @GetMapping()
    public List<Product> getAllProducts(){
        return guestService.getAllProducts();
    }


}
