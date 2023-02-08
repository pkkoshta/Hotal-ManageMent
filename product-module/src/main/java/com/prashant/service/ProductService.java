package com.prashant.service;

import com.prashant.Food;
import com.prashant.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Food> getByTag(String tag);

    List<Food> getByIds(List<Integer> ids);

     List<Food> getFoods();


    Food createFood(Food food);

    void deleteProduct(int pid);

    Optional<Product> getById(int id);

    Food getFoodById(int id);

    Food updateProduct(int pid, Food food);
}
