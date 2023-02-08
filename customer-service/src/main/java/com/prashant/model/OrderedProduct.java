package com.prashant.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(name = "ordered_product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int pid;

    private int customerId;
    private String name;


    private String imageUrl;

    private String description;
    private Double price;
    private boolean favorite;
    private int star;
    private String cookTime;


    private int quantity;

    /*public void setPrice(Double price) {
        this.price = price * quantity;
    }*/

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        ProductCustomer that = (ProductCustomer) o;
//        return pid == that.pid && name == that.name;
//    }
//
//    @Override
//    public int hashCode() {
//        return this.name.length() %10;
//    }
}
