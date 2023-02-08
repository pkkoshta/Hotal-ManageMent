package com.prashant.dto;


import com.prashant.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

        private int customerId;

        private String firstName;
        private String lastName;
        private String email;
        private double age;
        private String address;
        private long phone;
        private List<Product> products;

        private Double totalPrice;

    }


