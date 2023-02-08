package com.prashant.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prashant.Food;
import com.prashant.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {


    public static List<Object> foodList(List<Product> productList){

        List<Object> foods = productList.stream().map(Mapper::productToFood).collect(Collectors.toList());
        return foods;

    }
    public static Object foodToProdcut(Object object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(object, Product.class);

    }
    public static Object productToFood(Object object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(object, Food.class);

    }
}
