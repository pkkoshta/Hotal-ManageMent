package com.prashant.repository;

import com.prashant.entity.Product;
import com.prashant.model.ProductCustomer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface CustomerProductRepository extends JpaRepository<ProductCustomer, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete product_customer from product_customer join customer_tbl on product_customer.customer_p = customer_tbl.customer_id where id=?1", nativeQuery = true)
    void deleteProduct( int pid);

    List<ProductCustomer> findByCustomerId(int customerId);


}
