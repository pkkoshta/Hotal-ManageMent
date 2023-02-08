package com.prashant.dao;

import com.prashant.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    List<Product> findByTags(String tag);


}
