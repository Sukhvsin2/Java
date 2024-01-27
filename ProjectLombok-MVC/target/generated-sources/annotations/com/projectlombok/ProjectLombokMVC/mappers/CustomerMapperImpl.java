package com.projectlombok.ProjectLombokMVC.mappers;

import com.projectlombok.ProjectLombokMVC.entities.Customer;
import com.projectlombok.ProjectLombokMVC.model.CustomerDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-15T14:54:22-0500",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public CustomerDTO customerToCustomerDto(Customer customer) {
        if ( customer == null ) {
            return null;
        }

        CustomerDTO.CustomerDTOBuilder customerDTO = CustomerDTO.builder();

        customerDTO.id( customer.getId() );
        customerDTO.customerName( customer.getCustomerName() );
        customerDTO.version( customer.getVersion() );
        customerDTO.createdDate( customer.getCreatedDate() );
        customerDTO.lastModifiedDate( customer.getLastModifiedDate() );

        return customerDTO.build();
    }

    @Override
    public Customer customerDtoToCustomer(CustomerDTO customerDto) {
        if ( customerDto == null ) {
            return null;
        }

        Customer.CustomerBuilder customer = Customer.builder();

        customer.id( customerDto.getId() );
        customer.customerName( customerDto.getCustomerName() );
        customer.version( customerDto.getVersion() );
        customer.createdDate( customerDto.getCreatedDate() );
        customer.lastModifiedDate( customerDto.getLastModifiedDate() );

        return customer.build();
    }
}
