package com.example.shoppingproject.beans;

import com.example.shoppingproject.enums.Color;
import com.example.shoppingproject.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Min;
import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    private String description;

    private String image;


    @Column(nullable = true)
    private Color color;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private ProductType productType;

    @Column(nullable = false,scale = 2)
    @Min(0)
    private double price;

    private double averageRating;

    @Min(0)
    private int quantity;

    private int sales;

    private Date publishedDate;
    private Date ExpiredDate;
    @ManyToOne
    @ToString.Exclude
    private Company company;
}
