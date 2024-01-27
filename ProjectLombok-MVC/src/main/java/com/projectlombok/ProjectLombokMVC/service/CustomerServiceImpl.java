package com.projectlombok.ProjectLombokMVC.service;

import com.projectlombok.ProjectLombokMVC.model.CustomerDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService{
    private Map<UUID, CustomerDTO> customerList;

    public CustomerServiceImpl(){
        this.customerList = new HashMap<UUID, CustomerDTO>();
        CustomerDTO cust1 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Customer1")
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .build();
        CustomerDTO cust2 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Customer2")
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .build();
        CustomerDTO cust3 = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .version(1)
                .customerName("Customer3")
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .build();

        this.customerList.put(cust1.getId(), cust1);
        this.customerList.put(cust2.getId(), cust2);
        this.customerList.put(cust3.getId(), cust3);
    }

    @Override
    public void patchCustomerData(UUID id, CustomerDTO cust){
        CustomerDTO existing = customerList.get(id);

        if(StringUtils.hasText(cust.getCustomerName())){
            existing.setCustomerName(cust.getCustomerName());
        }

        if(cust.getVersion() != null){
            existing.setVersion(cust.getVersion());
        }
    }

    @Override
    public void deleteCustomer(UUID id){
        this.customerList.remove(id);
    }

    @Override
    public CustomerDTO updateCustomer(UUID id, CustomerDTO cust){
        CustomerDTO existingCust = this.customerList.get(id);
        existingCust.setCustomerName(cust.getCustomerName());
        existingCust.setVersion(cust.getVersion());
        existingCust.setLastModifiedDate(LocalDate.now());
        return existingCust;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        CustomerDTO savedCustomerDTO = CustomerDTO.builder()
                .id(UUID.randomUUID())
                .customerName(customerDTO.getCustomerName())
                .version(customerDTO.getVersion())
                .createdDate(LocalDate.now())
                .lastModifiedDate(LocalDate.now())
                .build();

        this.customerList.put(savedCustomerDTO.getId(), savedCustomerDTO);
        return savedCustomerDTO;
    }

    @Override
    public List<CustomerDTO> listCustomers() {
        return new ArrayList<CustomerDTO>(this.customerList.values());
    }

    @Override
    public CustomerDTO getCustomerById(UUID id) {
        return this.customerList.get(id);
    }

}
