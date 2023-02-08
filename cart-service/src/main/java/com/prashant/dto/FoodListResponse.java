package com.prashant.dto;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodListResponse {

    private List<Food> foods;

    private double totalPrice;

}
