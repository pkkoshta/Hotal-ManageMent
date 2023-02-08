package com.prashant.service;


import com.prashant.dto.CartRequest;
import com.prashant.dto.CustomerDTO;
import com.prashant.dto.Food;
import com.prashant.dto.FoodListResponse;
import com.prashant.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CartService {

    private static final Logger logger = LoggerFactory.getLogger(CartService.class);


    @Autowired
    private RestTemplate restTemplate;

    public FoodListResponse removeProductFromCart(CartRequest cartRequest){
        var foods = new ArrayList<Food>();
        var foodList = new FoodListResponse();
        logger.info("requesting for customer by cartRequest {}", cartRequest);
        CustomerDTO customerDTO = restTemplate.postForObject("http://localhost:9093/customer/api/removeProduct", cartRequest, CustomerDTO.class);
        logger.info("requesting for customerDTO{}", customerDTO);
        if (customerDTO !=null && customerDTO.getProducts().size() !=0){
            customerDTO.getProducts().stream()
                    .map(product -> foods.add(new Food(customerDTO.getCustomerId(), product.getId(), product.getName(), product.getImageUrl(), product.getPrice(),  product.getQuantity())))
                    .collect(Collectors.toList());
        }
        foodList.setFoods(foods);
        foodList.setTotalPrice(customerDTO.getTotalPrice());
        logger.info("responsed from customerDTO {}", customerDTO);

        return foodList;
    }

    public FoodListResponse getCustomerById(int id){
        var foods = new ArrayList<Food>();
        var foodList = new FoodListResponse();
        logger.info("requesting for customer by id {}", id);
        CustomerDTO customerDTO = restTemplate.getForObject("http://localhost:9093/customer/api/"+id, CustomerDTO.class);
        logger.info("requesting for customerDTO{}", customerDTO);
        if (customerDTO !=null && customerDTO.getProducts().size() !=0){
            customerDTO.getProducts().stream()
                    .map(product -> foods.add(new Food(customerDTO.getCustomerId(), product.getId(), product.getName(), product.getImageUrl(), product.getPrice(),  product.getQuantity())))
                    .collect(Collectors.toList());
        }
        foodList.setFoods(foods);
        foodList.setTotalPrice(customerDTO.getTotalPrice());
        logger.info("responsed from customerDTO {}", customerDTO);

        return foodList;
    }

    public FoodListResponse saveCart(CartRequest cartRequest){
        Food food = new Food();
        var foods = new ArrayList<Food>();
        var foodList = new FoodListResponse();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CartRequest> httpEntity = new HttpEntity<>(cartRequest, headers);
        double totalprice=0.0;
        logger.info("requesting for customer and updating product {}", cartRequest);
        CustomerDTO customerDTO = restTemplate.postForObject("http://localhost:9093/customer/api/customer/product",httpEntity, CustomerDTO.class);
        logger.info("responsed from customerDTO {}", customerDTO);
        if (customerDTO !=null && customerDTO.getProducts().size() !=0){
             customerDTO.getProducts().stream()
                   .map(product -> foods.add(new Food(cartRequest.getCustomerId(), product.getId(), product.getName(), product.getImageUrl(), product.getPrice(),  product.getQuantity())))
                   .collect(Collectors.toList());
        }
        for (Product p: customerDTO.getProducts()) {
            totalprice += p.getPrice();
        }
        foodList.setFoods(foods);
        logger.info("responsed from customerDTO {}", customerDTO.getTotalPrice());
        foodList.setTotalPrice(customerDTO.getTotalPrice());
        logger.info("responsed from customerDTO {}", customerDTO.getTotalPrice());

        return foodList;

    }

    public FoodListResponse completeOrder(int customerId) {
        Food food = new Food();
        var foods = new ArrayList<Food>();
        var foodList = new FoodListResponse();
        CustomerDTO customerDTO = restTemplate.getForObject("http://localhost:9093/customer/api/order/complete/"+customerId, CustomerDTO.class);
        if (customerDTO !=null && customerDTO.getProducts().size() !=0){
            customerDTO.getProducts().stream()
                    .map(product -> foods.add(new Food(customerId, product.getId(), product.getName(), product.getImageUrl(), product.getPrice(),  product.getQuantity())))
                    .collect(Collectors.toList());
        }

        foodList.setFoods(foods);
        logger.info("responsed from customerDTO {}", customerDTO.getTotalPrice());
        foodList.setTotalPrice(customerDTO.getTotalPrice());
        logger.info("responsed from customerDTO {}", customerDTO.getTotalPrice());

        return foodList;
    }
}
