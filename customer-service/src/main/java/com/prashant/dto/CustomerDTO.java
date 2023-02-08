package com.prashant.dto;


import com.prashant.model.OrderedProduct;
import com.prashant.model.ProductCustomer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

        private int customerId;

        private String firstName;
        private String lastName;
        private String password;
        private String email;
        private double age;
        private String address;

        private long phone;
        private Set<OrderedProduct> products;


        private Double totalPrice;

    }


