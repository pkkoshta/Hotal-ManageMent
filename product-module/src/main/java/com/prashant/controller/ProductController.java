package com.prashant.controller;

import com.prashant.Food;
import com.prashant.entity.Product;
import com.prashant.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/findByIds")
    public void getfoodByIds(@RequestBody List<String> ids){
        System.out.println(ids);
       //return productService.getByIds(ids);
    }

    @GetMapping("/tag/{tag}")
    public List<Food> productByTag(@PathVariable String tag){
        return productService.getByTag(tag);
    }
    @GetMapping
    public List<Food> productsList(){
        return productService.getFoods();
    }


    @GetMapping("/{id}")
    public Food getById(@PathVariable int id){

        return productService.getFoodById(id);
    }






    @PostMapping
    public Food createproduct(@RequestBody Food food){
        return productService.createFood(food);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }

    @PutMapping("/{id}")
    public Food editProduct(@PathVariable int id, @RequestBody Food food){
        return productService.updateProduct(id, food);
    }
}
