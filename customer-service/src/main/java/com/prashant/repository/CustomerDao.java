package com.prashant.repository;

import com.prashant.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

    Customer findByCustomerId(int id);


}
