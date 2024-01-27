package com.projectlombok.ProjectLombokMVC.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectlombok.ProjectLombokMVC.service.CustomerService;
import com.projectlombok.ProjectLombokMVC.service.CustomerServiceImpl;
import com.projectlombok.ProjectLombokMVC.model.CustomerDTO;
import java.util.*;

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

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    final String endPoint = "/api/v1/customer";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    CustomerServiceImpl customerServiceImpl;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup(){
        customerServiceImpl = new CustomerServiceImpl();
    }

    @Captor
    ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Captor
    ArgumentCaptor<CustomerDTO> customerArgumentCaptor;

    // to test the exception handling for customers
    @Test
    void testHandleNotFoundException() throws Exception{
        BDDMockito.given(customerService.getCustomerById(ArgumentMatchers.any(UUID.class))).willThrow(NotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer" + UUID.randomUUID()))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testPatchCustomer() throws Exception {
        CustomerDTO customerDTO = customerServiceImpl.listCustomers().get(0);

        Map<String, Object> customerMap = new HashMap<>();
        customerMap.put("customerName", "New Name");

        mockMvc.perform(MockMvcRequestBuilders.patch("/api/v1/customer/" + customerDTO.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMap)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(customerService).patchCustomerData(uuidArgumentCaptor.capture(),
                customerArgumentCaptor.capture());

        Assertions.assertThat(uuidArgumentCaptor.getValue()).isEqualTo(customerDTO.getId());
        Assertions.assertThat(customerArgumentCaptor.getValue().getCustomerName())
                .isEqualTo(customerMap.get("name"));
    }

    @Test
    void testDeleteCustomer() throws Exception{
        CustomerDTO cust = customerServiceImpl.listCustomers().get(0);

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/customer/"+cust.getId())
                .accept(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.status().isNoContent());

                Mockito.verify(customerService).deleteCustomer(uuidArgumentCaptor.capture());

        Assertions.assertThat(cust.getId()).isEqualTo(uuidArgumentCaptor.getValue());
    }

    @Test
    void testUpdateCustomer() throws Exception{
        CustomerDTO cust = customerServiceImpl.listCustomers().get(0);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/customer/" + cust.getId())
                        .content(objectMapper.writeValueAsString(cust))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(customerService).updateCustomer(cust.getId(), cust);
    }

    @Test
    void testCreateCustomer() throws Exception{
        CustomerDTO cust1 = customerServiceImpl.listCustomers().get(0);

        BDDMockito.given(customerService.saveCustomer(ArgumentMatchers.any(CustomerDTO.class))).willReturn(cust1);

        cust1.setId(null);
        cust1.setVersion(null);

        mockMvc.perform(MockMvcRequestBuilders.post(endPoint)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cust1)))
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.header().exists("Location"));
    }

    @Test
    void testListCustomers() throws Exception{
        BDDMockito.given(customerService.listCustomers()).willReturn(customerServiceImpl.listCustomers());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()", CoreMatchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id", CoreMatchers.is(customerServiceImpl.listCustomers().get(0).getId().toString())));
    }

    @Test
    void testCustomerById() throws Exception{
        CustomerDTO cust1 = customerServiceImpl.listCustomers().get(0);

        BDDMockito.given(customerService.getCustomerById(cust1.getId())).willReturn(cust1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/customer/" + cust1.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(cust1.getId().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerName", CoreMatchers.is(cust1.getCustomerName())));
    }
}
