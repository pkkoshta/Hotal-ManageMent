package com.prashant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @SequenceGenerator(name = "cartGen", sequenceName = "cartSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "cartGen")
    private int cartId;
    private int customerId;
    private int foodId;

    private int quantity;

    private double totalPrice;
}
