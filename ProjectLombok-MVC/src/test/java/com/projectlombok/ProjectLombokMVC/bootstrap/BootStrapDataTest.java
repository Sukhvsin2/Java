package com.projectlombok.ProjectLombokMVC.bootstrap;

import com.projectlombok.ProjectLombokMVC.repositories.BeerRepository;
import com.projectlombok.ProjectLombokMVC.repositories.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BootStrapDataTest {
    @Autowired
    BeerRepository beerRepository;

    @Autowired
    CustomerRepository customerRepository;

    BootStrapData bootStrapData;

    @BeforeEach
    void setup(){
        bootStrapData = new BootStrapData(beerRepository, customerRepository);
    }

    @Test
    void run() throws Exception {
        bootStrapData.run(null);

        assertThat(beerRepository.count()).isEqualTo(3);
        assertThat(customerRepository.count()).isEqualTo(3);
    }

}
