package com.projectlombok.ProjectLombokMVC.bootstrap;

import com.projectlombok.ProjectLombokMVC.entities.Beer;
import com.projectlombok.ProjectLombokMVC.entities.Customer;
import com.projectlombok.ProjectLombokMVC.model.BeerStyle;
import com.projectlombok.ProjectLombokMVC.repositories.BeerRepository;
import com.projectlombok.ProjectLombokMVC.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {
    private final BeerRepository beerRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBeerData();
        loadCustomerData();
    }

    public void loadBeerData(){
        if(beerRepository.count() != 0)
            return;

        Beer beer1 = Beer.builder()
                .beerName("Beer1")
                .beerStyle(BeerStyle.GOSE)
                .upc("1234")
                .price(new BigDecimal("12.0"))
                .quantityOnHand(12)
                .updateDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();

        Beer beer2 = Beer.builder()
                .beerName("Beer2")
                .beerStyle(BeerStyle.GOSE)
                .upc("5678")
                .price(new BigDecimal("11.50"))
                .quantityOnHand(10)
                .updateDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();

        Beer beer3 = Beer.builder()
                .beerName("Beer3")
                .beerStyle(BeerStyle.GOSE)
                .upc("1287")
                .price(new BigDecimal("11.0"))
                .quantityOnHand(1)
                .updateDate(LocalDateTime.now())
                .createdDate(LocalDateTime.now())
                .build();

        beerRepository.saveAll(List.of(beer1,beer2, beer3));
    }
    public void loadCustomerData(){
        if(customerRepository.count() != 0)
            return;

        Customer cust1 = Customer.builder()
                .customerName("cust1")
                .lastModifiedDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .build();
        Customer cust2 = Customer.builder()
                .customerName("cust2")
                .lastModifiedDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .build();
        Customer cust3 = Customer.builder()
                .customerName("cust3")
                .lastModifiedDate(LocalDate.now())
                .createdDate(LocalDate.now())
                .build();

        customerRepository.saveAll(List.of(cust1, cust2, cust3));
    }
}
