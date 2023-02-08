package com.prashant.mapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prashant.dto.CustomerDTO;
import com.prashant.model.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

    public static List<CustomerDTO> dtos(List<Customer> customers){
        return customers.stream().map(CustomerMapper::customerToDTO).collect(Collectors.toList());
    }
    public static CustomerDTO customerToDTO(Customer customer){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(customer, CustomerDTO.class);
    }

    public static Customer dtoTOCustomer(CustomerDTO customer){
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper.convertValue(customer, Customer.class);
    }
}
