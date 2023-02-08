package com.prashant.mapper;

import com.prashant.dto.CustomerDTO;
import com.prashant.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapperCustomer {

    MapperCustomer instance = Mappers.getMapper(MapperCustomer.class);

    @Mapping(source = "foodId", target = "foodId")
    Customer customerDTOtoCustomer(CustomerDTO dto);
}
