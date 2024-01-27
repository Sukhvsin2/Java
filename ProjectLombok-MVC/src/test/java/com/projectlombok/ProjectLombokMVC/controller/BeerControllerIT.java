package com.projectlombok.ProjectLombokMVC.controller;

import com.projectlombok.ProjectLombokMVC.model.BeerDTO;
import com.projectlombok.ProjectLombokMVC.repositories.BeerRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@SpringBootTest
public class BeerControllerIT {
    @Autowired
    BeerController beerController;

    @Autowired
    BeerRepository beerRepository;

    @Test
    void testListBeers(){
        List<BeerDTO> dtos = beerController.listBeers();

        Assertions.assertThat(dtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test()
    void testEmptyList(){
        beerRepository.deleteAll();
        List<BeerDTO> dtos = beerController.listBeers();
        Assertions.assertThat(dtos.size()).isEqualTo(0);
    }
}
