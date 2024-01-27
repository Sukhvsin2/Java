package com.projectlombok.ProjectLombokMVC.service;

import com.projectlombok.ProjectLombokMVC.mappers.BeerMapper;
import com.projectlombok.ProjectLombokMVC.model.BeerDTO;
import com.projectlombok.ProjectLombokMVC.repositories.BeerRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Primary
@AllArgsConstructor
public class BeerServiceJPA implements BeerService {
    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    /**
     * Finding all the beer objects from the repository in a list format
     * but, we need to provide a list of DTOs
     * stream will provide the stream of data
     * .map will do the conversion from Beer Entity to BeerDTO object
     * and, then collects as a list
     * **/
    @Override
    public List<BeerDTO> listBeers() {
        return beerRepository.findAll()
                .stream()
                .map(beerMapper::beerToBeerDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BeerDTO> getBeerById(UUID id){
        return Optional.ofNullable(beerMapper.beerToBeerDto(beerRepository.findById(id).orElse(null)));
    }

    @Override
    public BeerDTO saveBeer(BeerDTO beerDTO) {
        return null;
    }

    @Override
    public void updateBeer(UUID id, BeerDTO beerDTO) {

    }

    @Override
    public void deleteBeer(UUID id) {

    }

    @Override
    public void patchBeerById(UUID id, BeerDTO beerDTO) {

    }
}
