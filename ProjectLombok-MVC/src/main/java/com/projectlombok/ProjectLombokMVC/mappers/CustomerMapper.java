package com.projectlombok.ProjectLombokMVC.mappers;

import com.projectlombok.ProjectLombokMVC.entities.Customer;
import com.projectlombok.ProjectLombokMVC.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDTO customerToCustomerDto(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDto);
}
