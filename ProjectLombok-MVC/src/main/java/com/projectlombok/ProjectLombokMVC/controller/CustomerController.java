package com.projectlombok.ProjectLombokMVC.controller;

import com.projectlombok.ProjectLombokMVC.model.CustomerDTO;
import com.projectlombok.ProjectLombokMVC.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class CustomerController {
    public static final String CUSTOMER_PATH = "/api/v1/customer";
    public static final String CUSTOMER_PATH_ID = CUSTOMER_PATH + "/{id}";

    private final CustomerService customerService;

    @PatchMapping(CUSTOMER_PATH_ID)
    public ResponseEntity patchCustomer(@PathVariable("id") UUID id, CustomerDTO cust){
        this.customerService.patchCustomerData(id, cust);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(CUSTOMER_PATH_ID)
    public ResponseEntity deleteCustomer(@PathVariable("id")UUID id){
        this.customerService.deleteCustomer(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping(CUSTOMER_PATH_ID)
    public ResponseEntity updateCustomer(@PathVariable("id")UUID id, @RequestBody CustomerDTO cust){
        this.customerService.updateCustomer(id, cust);

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    @PostMapping(CUSTOMER_PATH)
    public ResponseEntity postCustomer(@RequestBody CustomerDTO customerDTO){
        CustomerDTO savedCustomerDTO = this.customerService.saveCustomer(customerDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/customer/" + savedCustomerDTO.getId());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping(CUSTOMER_PATH)
    public List<CustomerDTO> listCustomers(){
        return this.customerService.listCustomers();
    }

    @GetMapping(CUSTOMER_PATH_ID)
    public CustomerDTO getCustomerById(@PathVariable("id") UUID id){
        return this.customerService.getCustomerById(id);
    }
}
