package com.example.backendproject.api;

import com.example.backendproject.domain.Building;
import com.example.backendproject.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuildingRepository extends JpaRepository<Building,Long> {
    Building findByBname(String name);
}
