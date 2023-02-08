package com.prashant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    @ElementCollection
    private String[] tags;
    private String imageUrl;

    private String description;
    private Double price;
    private boolean favorite;
    private int star;
    private String cookTime;
    @ElementCollection
    private String[] origins;

    private int quantity;




}
