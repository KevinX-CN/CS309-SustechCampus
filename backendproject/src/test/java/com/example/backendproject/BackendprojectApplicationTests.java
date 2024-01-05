package com.example.backendproject;

import com.example.backendproject.domain.ShopList;
import com.example.backendproject.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BackendprojectApplicationTests {
    @Autowired
    ShopService shopService;
    @Test
    void contextLoads() {
        List<ShopList> tem = shopService.findAll();
        for (int i = 0; i < tem.size(); i++) {
            System.out.println(tem.get(i));
        }
    }

}
