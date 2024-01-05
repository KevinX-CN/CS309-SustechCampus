package com.example.backendproject.api;

import com.example.backendproject.domain.PurchaseRecord;
import com.example.backendproject.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReserveRepository extends JpaRepository<Reserve,Long> {
    List<Reserve> findAll();

    Reserve findByUsernameAndRoomnameAndDataAndTime(String username,String roomname,String data,String time);

    List<Reserve> findByUsername(String username);
}
