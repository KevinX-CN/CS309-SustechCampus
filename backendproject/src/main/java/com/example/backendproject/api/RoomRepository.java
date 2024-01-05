package com.example.backendproject.api;

import com.example.backendproject.domain.Room;
import com.example.backendproject.domain.ShopList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findAll();

    List<Room> findByBuildingAndFloor(String building,String name);

}
