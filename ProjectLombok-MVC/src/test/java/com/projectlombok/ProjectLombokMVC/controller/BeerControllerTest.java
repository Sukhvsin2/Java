package com.projectlombok.ProjectLombokMVC.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectlombok.ProjectLombokMVC.model.BeerDTO;
import com.projectlombok.ProjectLombokMVC.service.BeerService;
import com.projectlombok.ProjectLombokMVC.service.BeerServiceImpl;
import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

//@SpringBootTest
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    BeerServiceImpl beerServiceImpl;

    @BeforeEach
    void setUp(){
        beerServiceImpl = new BeerServiceImpl();
    }

    @Autowired
    ObjectMapper objectMapper;

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<BeerDTO> beerArgumentCaptor;

    // Testing Exception Handling
    @Test
    void getBeerByIdNotFound() throws Exception{
        BDDMockito.given(beerService.getBeerById(ArgumentMatchers.any(UUID.class))).willThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(BeerController.BEER_PATH + "/" + UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testPatchBeer() throws Exception {
        BeerDTO beerDTO = beerServiceImpl.listBeers().get(0);
        HashMap<String, Object> beerMap = new HashMap<>();
        beerMap.put("beerName", "New Beer Name");

        mockMvc.perform(MockMvcRequestBuilders.patch(BeerController.BEER_PATH + "/" + beerDTO.getId())
                .content(objectMapper.writeValueAsString(beerMap))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(beerService).patchBeerById(uuidArgumentCaptor.capture(), beerArgumentCaptor.capture());

        Assertions.assertThat(beerDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
        Assertions.assertThat(beerMap.get("beerName")).isEqualTo(beerArgumentCaptor.getValue().getBeerName());
    }

    @Test
    void testDeleteBeer() throws Exception{
        BeerDTO beerDTO = beerServiceImpl.listBeers().get(0);

        mockMvc.perform(MockMvcRequestBuilders.delete(BeerController.BEER_PATH + "/" + beerDTO.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        uuidArgumentCaptor = ArgumentCaptor.forClass(UUID.class);
        Mockito.verify(beerService).deleteBeer(uuidArgumentCaptor.capture());

        Assertions.assertThat(beerDTO.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testUpdateBeer() throws Exception{
        BeerDTO beerDTO = beerServiceImpl.listBeers().get(0);

        mockMvc.perform(put(BeerController.BEER_PATH + "/" + beerDTO.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(beerService).updateBeer(ArgumentMatchers.any(UUID.class), ArgumentMatchers.any(BeerDTO.class));
    }

    @Test
    void testCreateBeer() throws JsonProcessingException, Exception {
        BeerDTO beerDTO = beerServiceImpl.listBeers().get(0);
        beerDTO.setVersion(null);
        beerDTO.setId(null);
        //System.out.println(objectMapper.writeValueAsString(beer));

        BDDMockito.given(beerService.saveBeer(ArgumentMatchers.any(BeerDTO.class))).willReturn(beerServiceImpl.listBeers().get(1));

        mockMvc.perform(MockMvcRequestBuilders.post(BeerController.BEER_PATH)
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(beerDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    void testListBeers() throws Exception {
        BDDMockito.given(beerService.listBeers()).willReturn(beerServiceImpl.listBeers());

        mockMvc.perform(get(BeerController.BEER_PATH)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", CoreMatchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", CoreMatchers.is(beerServiceImpl.listBeers().get(0).getId().toString())));
    }

    @Test
    void getBeerById() throws Exception {
        BeerDTO testBeer = beerServiceImpl.listBeers().get(0);

        BDDMockito.given(beerService.getBeerById(testBeer.getId())).willReturn(null);

        mockMvc.perform(get(BeerController.BEER_PATH_ID, testBeer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(testBeer.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.beerName", CoreMatchers.is(testBeer.getBeerName())));
    }
}
