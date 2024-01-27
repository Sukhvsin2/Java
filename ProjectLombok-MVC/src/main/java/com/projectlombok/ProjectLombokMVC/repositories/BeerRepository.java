package com.projectlombok.ProjectLombokMVC.repositories;

import com.projectlombok.ProjectLombokMVC.entities.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BeerRepository extends JpaRepository<Beer, UUID> {
}
