package com.example.backendproject.service;

import com.example.backendproject.api.ShopListRepository;
import com.example.backendproject.domain.ShopList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopListRepository shopListRepository;

    public List<ShopList> findAll(){
        return shopListRepository.findAll();
    }
    public ShopList save(ShopList shopList) {
        return shopListRepository.save(shopList);
    }


}
