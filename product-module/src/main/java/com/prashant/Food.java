package com.prashant;

import lombok.*;

@Data
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Food {
    private int id;
    private String name;
    private String[] tags;
    private String imageUrl;
    private Double price;

    private boolean favorite;
    private int star;
    private String cookTime;
    private String[] origins;

    private int quantity;
}
