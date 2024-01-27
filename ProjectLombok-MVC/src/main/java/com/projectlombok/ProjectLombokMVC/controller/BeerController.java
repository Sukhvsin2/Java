package com.projectlombok.ProjectLombokMVC.controller;

import com.projectlombok.ProjectLombokMVC.entities.Beer;
import com.projectlombok.ProjectLombokMVC.model.BeerDTO;
import com.projectlombok.ProjectLombokMVC.service.BeerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class BeerController {
    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{id}";

    private final BeerService beerService;

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity patchBeerById(@PathVariable("id") UUID id, @RequestBody BeerDTO beerDTO){
        this.beerService.patchBeerById(id, beerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
    public ResponseEntity deleteBeer(@PathVariable("id")UUID id){
        this.beerService.deleteBeer(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(BEER_PATH_ID)
    public ResponseEntity putBeer(@PathVariable("id") UUID id, @RequestBody BeerDTO beerDTO){
        this.beerService.updateBeer(id, beerDTO);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping(BEER_PATH)
    public ResponseEntity postBeer(@RequestBody BeerDTO beerDTO){
        BeerDTO savedBeerDTO = this.beerService.saveBeer(beerDTO);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeerDTO.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping(BEER_PATH)
    public List<BeerDTO> listBeers(){
        return this.beerService.listBeers();
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBearById(@PathVariable("id") UUID id){
        return beerService.getBeerById(id).orElseThrow(NotFoundException::new);
    }
}
