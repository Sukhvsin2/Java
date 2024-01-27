package com.projectlombok.ProjectLombokMVC.repositories;

import com.projectlombok.ProjectLombokMVC.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
