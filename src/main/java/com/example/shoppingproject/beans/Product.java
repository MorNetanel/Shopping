package com.example.shoppingproject.beans;

import com.example.shoppingproject.enums.Color;
import com.example.shoppingproject.enums.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.sql.Date;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    private String description;

    private String image;

    @Enumerated(EnumType.STRING)
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

    private Date publishedDate, ExpiredDate;
    @ManyToOne
    private Company company;
}
