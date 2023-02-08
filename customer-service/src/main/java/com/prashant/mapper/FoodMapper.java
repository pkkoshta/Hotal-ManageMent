package com.prashant.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prashant.Food;
import com.prashant.model.OrderedProduct;
import com.prashant.model.ProductCustomer;
import jakarta.persistence.criteria.Order;

import java.util.List;
import java.util.stream.Collectors;

public class FoodMapper {


    public static List<Object> foodList(List<ProductCustomer> productList){

        List<Object> foods = productList.stream().map(FoodMapper::productToFood).collect(Collectors.toList());
        return foods;

    }

    public static Object foodToOrderProduct(Object object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(object, OrderedProduct.class);

    }
    public static Object foodToProdcut(Object object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(object, ProductCustomer.class);

    }
    public static Object productToFood(Object object){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(object, Food.class);

    }
}
