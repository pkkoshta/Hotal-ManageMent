package com.prashant.dto;

import lombok.*;

@Data
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    private int customerId;
    private int foodId;
    private String name;

    private String imageUrl;
    private Double price;

    private int quantity;

    private double totalPrice;


    public Food(int customerId, int foodId, String name, String imageUrl, Double price, int quantity) {
        this.customerId = customerId;
        this.foodId = foodId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.quantity = quantity;
       // this.totalPrice  = totalPrice;
    }
}
