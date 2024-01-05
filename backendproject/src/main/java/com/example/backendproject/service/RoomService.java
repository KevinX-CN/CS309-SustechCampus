package com.example.backendproject.service;

import com.example.backendproject.api.CommentRepository;
import com.example.backendproject.api.RoomRepository;
import com.example.backendproject.domain.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAll(){
       return roomRepository.findAll();
    }

    public List<Room> findByBuildingAndFloor(String building,String name){
        return roomRepository.findByBuildingAndFloor(building, name);
    }
}
