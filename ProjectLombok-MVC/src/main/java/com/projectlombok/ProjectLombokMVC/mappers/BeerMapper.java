package com.projectlombok.ProjectLombokMVC.mappers;

import com.projectlombok.ProjectLombokMVC.entities.Beer;
import com.projectlombok.ProjectLombokMVC.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BeerMapper {
    Beer beerDtoTOBeer(BeerDTO dto);
    BeerDTO beerToBeerDto(Beer beer);
}
