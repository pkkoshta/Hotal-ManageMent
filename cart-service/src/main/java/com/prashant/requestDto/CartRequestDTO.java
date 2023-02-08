package com.prashant.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartRequestDTO {

    private int productId;

    private int quantity;

    private int customerId;
}
