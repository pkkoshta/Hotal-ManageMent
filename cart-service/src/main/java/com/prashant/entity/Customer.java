package com.prashant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "customer_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @SequenceGenerator(name = "customer", sequenceName = "customerSeq", initialValue = 1, allocationSize = 100)
    @GeneratedValue(generator = "customer")
    private int customerId;

    private String firstName;
    private String lastName;
    private String email;
    private double age;

    private String address;
    private long phone;



    @OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_p", referencedColumnName = "customerId")
    private List<Product> products;
    private int paymentId;

}
