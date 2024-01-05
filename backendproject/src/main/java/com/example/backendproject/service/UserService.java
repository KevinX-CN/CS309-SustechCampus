package com.example.backendproject.service;

import com.example.backendproject.api.UersRepository;
import com.example.backendproject.domain.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UersRepository uersRepository;

    public List<Users> findAll(){
        return uersRepository.findAll();
    };
    public Users findByName(String name){
        return uersRepository.findByName(name);
    };
    public Users save(Users users){
       return uersRepository.save(users);
    }

    public List<Users> findByType(String type){
        return uersRepository.findByType(type);
    }
}
