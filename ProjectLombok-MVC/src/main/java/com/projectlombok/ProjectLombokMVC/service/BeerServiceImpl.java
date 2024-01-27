package com.projectlombok.ProjectLombokMVC.service;

import com.projectlombok.ProjectLombokMVC.model.BeerDTO;
import com.projectlombok.ProjectLombokMVC.model.BeerStyle;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BeerServiceImpl implements BeerService{
    private final Map<UUID, BeerDTO> beerMap;

    public BeerServiceImpl(){
        this.beerMap = new HashMap<UUID, BeerDTO>();

        BeerDTO bear1 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Alo")
                .beerStyle(BeerStyle.LAGER)
                .version(1)
                .price(new BigDecimal("12.0"))
                .createdDate(LocalDateTime.now())
                .quantityOnHand(12)
                .updateDate(LocalDateTime.now())
                .upc("12345")
                .build();
        BeerDTO bear2 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Alo2")
                .beerStyle(BeerStyle.LAGER)
                .version(1)
                .price(new BigDecimal("12.0"))
                .createdDate(LocalDateTime.now())
                .quantityOnHand(12)
                .updateDate(LocalDateTime.now())
                .upc("12345")
                .build();
        BeerDTO bear3 = BeerDTO.builder()
                .id(UUID.randomUUID())
                .beerName("Alo3")
                .beerStyle(BeerStyle.LAGER)
                .version(1)
                .price(new BigDecimal("12.0"))
                .createdDate(LocalDateTime.now())
                .quantityOnHand(12)
                .updateDate(LocalDateTime.now())
                .upc("12345")
                .build();

        this.beerMap.put(bear1.getId(), bear1);
        this.beerMap.put(bear2.getId(), bear2);
        this.beerMap.put(bear3.getId(), bear3);
    }

    @Override
    public void patchBeerById(UUID id, BeerDTO beerDTO){
        BeerDTO existing = beerMap.get(id);;

        if(StringUtils.hasText(beerDTO.getBeerName())){
            existing.setBeerName(beerDTO.getBeerName());
        }

        if(beerDTO.getBeerStyle() != null){
            existing.setBeerStyle(beerDTO.getBeerStyle());
        }

        if(beerDTO.getQuantityOnHand() != null){
            existing.setQuantityOnHand(beerDTO.getQuantityOnHand());
        }

        if(StringUtils.hasText(beerDTO.getUpc())){
            existing.setUpc(beerDTO.getUpc());
        }
        beerDTO.setUpdateDate(LocalDateTime.now());
    }

    @Override
    public void deleteBeer(UUID id){
        this.beerMap.remove(id);
    }

    @Override
    public void updateBeer(UUID id, BeerDTO beerDTO) {
        BeerDTO existingBeerDTO = beerMap.get(id);
        existingBeerDTO.setBeerName(beerDTO.getBeerName());
        existingBeerDTO.setBeerStyle(beerDTO.getBeerStyle());
        existingBeerDTO.setQuantityOnHand(beerDTO.getQuantityOnHand());
        existingBeerDTO.setPrice(beerDTO.getPrice());
        existingBeerDTO.setUpc(beerDTO.getUpc());
        existingBeerDTO.setUpdateDate(LocalDateTime.now());
    }

    @Override
    public BeerDTO saveBeer(BeerDTO beerDTO) {
        BeerDTO saveBeerDTO = BeerDTO.builder()
                .id(UUID.randomUUID())
                .version(beerDTO.getVersion())
                .beerStyle(beerDTO.getBeerStyle())
                .upc(beerDTO.getUpc())
                .beerName(beerDTO.getBeerName())
                .createdDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .quantityOnHand(beerDTO.getQuantityOnHand())
                .price(beerDTO.getPrice())
                .build();

        this.beerMap.put(saveBeerDTO.getId(), saveBeerDTO);

        return saveBeerDTO;
    }

    @Override
    public List<BeerDTO> listBeers(){
        return new ArrayList<BeerDTO>(beerMap.values());
    }
    @Override
    public Optional<BeerDTO> getBeerById(UUID id) {
        return Optional.of(beerMap.get(id));
    }
}
