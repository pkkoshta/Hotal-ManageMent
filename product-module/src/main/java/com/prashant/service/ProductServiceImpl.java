package com.prashant.service;

import com.prashant.dao.ProductDao;
import com.prashant.Food;
import com.prashant.entity.Product;
import com.prashant.exception.ProductNotFoundException;
import com.prashant.mapper.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductDao productDao;


    @Override
    public List<Food> getByTag(String tag) {
        List<Product> productList = null;
        var foodList =new ArrayList<Food>();
        if (!StringUtils.hasText(tag))
            productList = productDao.findByTags(tag);
         Mapper.foodList(productList).forEach(food-> foodList.add((Food) food));

         return foodList;

    }

    @Override
    public List<Food> getByIds(List<Integer> ids) {
        List<Food> foodList = new ArrayList<>();
        List<Product> productList = productDao.findAllById(ids);
        Mapper.foodList(productList).forEach(food-> foodList.add((Food) food));
        return foodList;
    }

    @Override
    public List<Food> getFoods() {
        List<Food> foodList = new ArrayList<>();
        List<Product> productList = productDao.findAll();
        Mapper.foodList(productList).forEach(food-> foodList.add((Food) food));
        return foodList;
    }

    @Override
    public Food createFood(Food food) {
        Product product = (Product) Mapper.foodToProdcut(food);
        Product savedProduct = productDao.save(product);
        Food food1 = (Food) Mapper.productToFood(savedProduct);
        return food1;
    }



    @Override
    public void deleteProduct(int pid) {
        productDao.deleteById(pid);
    }

    @Override
    public Optional<Product> getById(int id) {
        return productDao.findById(id);
    }

    @Override
    public Food getFoodById(int id) {
        Optional<Product> byId = getById(id);
        LOGGER.info("Get By Id {}", byId.get().getId());
        Food food = (Food) Mapper.productToFood(byId.get());
        LOGGER.info("mapped to food {}", food);
        return food;
    }

    @Override
    public Food updateProduct(int pid, Food food) {
       Optional<Product> product1 = getById(pid);
        Food food1 = null;
        Product savedProduct = null;
        if (product1.isEmpty())
            product1.orElseThrow(()-> new ProductNotFoundException("Product not found..."));

        if (product1.isPresent()){
           Product product = (Product) Mapper.foodToProdcut(food);
           savedProduct = productDao.save(product);
            food1 = (Food) Mapper.productToFood(savedProduct);
       }

       return food1;
    }


}
