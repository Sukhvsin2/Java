package com.projectlombok.ProjectLombokMVC.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
public class BeerDTO {
    private UUID id;
    private Integer version;
    private String beerName;
    private BeerStyle beerStyle;
    private Integer quantityOnHand;
    private BigDecimal price;
    private String upc;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
