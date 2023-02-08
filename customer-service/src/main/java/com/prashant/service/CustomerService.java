package com.prashant.service;

import com.prashant.dto.CustomerDTO;
import com.prashant.dto.CartRequest;

import java.util.List;

public interface CustomerService {
    public CustomerDTO orderComplete(int customerId);
    public CustomerDTO removeProduct(CartRequest cartRequest);
    public List<CustomerDTO> customers();

    CustomerDTO checkCustomerOrderById(int id);

    CustomerDTO createCustomer(CustomerDTO dto);

    CustomerDTO updateCustomer(CartRequest dto);

    void deleteCustomer(int cid);

    CustomerDTO getCustomerById(int id);

    String updateAndCreateFoodId(int custId, int foodId);
}
