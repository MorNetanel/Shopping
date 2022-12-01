package com.example.shoppingproject.beans;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    @Size(min = 8, max = 30)
    private String email;

    @Column(unique = true, nullable = false)
    @Size(min = 4, max = 16)
    private String userName;

    @Column(nullable = false)
    @Size(min = 4, max = 16)
    private String password;

    @Column(nullable = false)
    @Size(min = 3, max = 10)
    private String firstName;

    @Column(nullable = false)
    @Size(min = 3, max = 10)
    private String LastName;
    @ManyToMany
    @Singular("customerProduct")
    private List<Product> products = new ArrayList<>();
    @ManyToMany
    @Singular("cartProduct") // provide methods like add product to cart, add add list of products and clear
    private List<Product>cart = new ArrayList<>();
    private boolean isVerified, isHelper;
    private Date birthDate;


}
