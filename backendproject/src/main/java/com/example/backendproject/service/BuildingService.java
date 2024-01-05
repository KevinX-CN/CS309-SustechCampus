package com.example.backendproject.service;

import com.example.backendproject.api.BuildingRepository;
import com.example.backendproject.domain.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    @Autowired
    BuildingRepository buildingRepository;

    public Building findByBname(String name){
        return buildingRepository.findByBname(name);
    }
}
