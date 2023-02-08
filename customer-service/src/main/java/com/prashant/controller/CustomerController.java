package com.prashant.controller;

import com.prashant.dto.CustomerDTO;
import com.prashant.dto.CartRequest;
import com.prashant.model.User;
import com.prashant.service.CustomerService;
import com.prashant.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/customer/api")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @GetMapping("/check/customerOrder/{customerId}")
    public CustomerDTO getOrderDetails(@PathVariable int customerId){
        return customerService.checkCustomerOrderById(customerId);
    }

    @GetMapping("/order/complete/{customerId}")
    public CustomerDTO orderComplete(@PathVariable int customerId){
        return customerService.orderComplete(customerId);
    }

    @PostMapping("/removeProduct")
    public CustomerDTO removeProduct(@RequestBody CartRequest cartRequest){
        return customerService.removeProduct(cartRequest);
    }

    @GetMapping("/food/{custId}/{id}")
    public String createAndUpdateFoodId(@PathVariable("custId") int custId ,@PathVariable("id") int foodId){
        log.warn("get food id {}", foodId);
       return customerService.updateAndCreateFoodId(custId,foodId);
    }

    @GetMapping("/{id}")
    public CustomerDTO getByCustomer(@PathVariable int id){
        log.warn("get customer {}", id);
        return customerService.getCustomerById(id);
    }
    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO dto){
        log.info("create customer {}", dto);
        return customerService.createCustomer(dto);
    }

    @PostMapping("/customer/product")
    public CustomerDTO updateCustomer(@RequestBody CartRequest dto){

        return  customerService.updateCustomer(dto);
    }

    @GetMapping
    public List<CustomerDTO> customerDTOList(){

        return customerService.customers();
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable int id){
        log.warn("delete customer {}", id);
        customerService.deleteCustomer(id);
    }
}
