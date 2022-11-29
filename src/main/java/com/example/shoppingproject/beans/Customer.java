package com.example.shoppingproject.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email, userName, password, firstName, LastName;
    @ManyToMany
    private List<Product> products;
    @ManyToMany
    private List<Product>cart;
    private boolean isVerified, isHelper;
    private Date birthDate;


}
