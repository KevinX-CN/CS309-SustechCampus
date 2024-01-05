package com.example.backendproject.service;

import com.example.backendproject.api.ReserveRepository;
import com.example.backendproject.domain.Reserve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReserveService {
    @Autowired
    ReserveRepository reserveRepository;

    public List<Reserve> findAll(){
        return reserveRepository.findAll();
    }

    public Reserve findByUsernameAndRoomnameAndDataAndTime(String username ,String roomname,String data,String time){
        return reserveRepository.findByUsernameAndRoomnameAndDataAndTime(username, roomname, data, time);
    }

    public List<Reserve> findByUsername(String username){
        return reserveRepository.findByUsername(username);
    }

    public void delete(Reserve reserve){
        reserveRepository.delete(reserve);
    }
    public Reserve save(Reserve reserve){
        return reserveRepository.save(reserve);
    }
}
