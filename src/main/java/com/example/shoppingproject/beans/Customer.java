package com.example.shoppingproject.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
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
    private List<Product> products;
    @ManyToMany
    private List<Product>cart;
    private boolean isVerified, isHelper;
    private Date birthDate;


}
