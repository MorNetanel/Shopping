package com.example.shoppingproject.beans;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Company {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
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

    private boolean isVerified;

    @Column(unique = true)
    @Size(min = 4, max = 16)
    private String companyName;

    @OneToMany (mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Singular("companyProduct")
    private List<Product> ProductList = new ArrayList<>();

}
