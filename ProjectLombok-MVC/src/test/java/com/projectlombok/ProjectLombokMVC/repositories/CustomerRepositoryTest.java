package com.projectlombok.ProjectLombokMVC.repositories;

import com.projectlombok.ProjectLombokMVC.entities.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void testSaveCustomer(){
        Customer saveCust = customerRepository.save(Customer.builder()
                .customerName("New Cust name for repo").build());

        Assertions.assertThat(saveCust).isNotNull();
        Assertions.assertThat(saveCust.getId()).isNotNull();
    }
}