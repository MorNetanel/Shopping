package com.example.shoppingproject.beans;

import com.example.shoppingproject.enums.Color;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String productName, description, image;
    private Color color;
    private double price, averageRating;
    private int quantity, sales;
    private Date publishedDate, ExpiredDate;
    @ManyToOne
    private Company company;
}
