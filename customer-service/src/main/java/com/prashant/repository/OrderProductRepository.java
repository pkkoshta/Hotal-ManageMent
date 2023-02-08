package com.prashant.repository;

import com.prashant.model.OrderedProduct;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderProductRepository extends JpaRepository<OrderedProduct, Integer> {

    @Modifying
    @Transactional
    @Query(value = "delete from ordered_product where customer_id=?1 and pid =?2", nativeQuery = true)
    void deleteByCustomerIdAndProductId(int customerId, int productId);

    List<OrderedProduct> findByCustomerId(int customerId);


}
