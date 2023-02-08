package com.prashant.controller;

import com.prashant.dto.CartRequest;
import com.prashant.dto.Food;
import com.prashant.dto.FoodListResponse;
import com.prashant.entity.Cart;
import com.prashant.requestDto.CartRequestDTO;
import com.prashant.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/cartService")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);


    @Autowired
    private CartService cartService;

    @GetMapping("/completeOrder/{customerId}")
    public FoodListResponse completeOrder(@PathVariable int customerId){
        return cartService.completeOrder(customerId);
    }

    @GetMapping("/remove/{customerId}/{productId}")
    public FoodListResponse deleteCartProduct(@PathVariable int customerId, @PathVariable int productId){
        CartRequest cartRequest = new CartRequest(customerId, productId, 0);
        return cartService.removeProductFromCart(cartRequest);
    }
    @GetMapping("/customerid/{cid}")
    public FoodListResponse getCustomerFoodByCid(@PathVariable int cid){
    return cartService.getCustomerById(cid);
    }


    @PostMapping("/saveCart")
    public FoodListResponse saveCart(@RequestBody CartRequest cartRequest){
        return cartService.saveCart(cartRequest);
    }


}


