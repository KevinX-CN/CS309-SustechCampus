package com.example.backendproject.api;

import com.example.backendproject.domain.PurchaseRecord;
import com.example.backendproject.domain.ShopList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopListRepository extends JpaRepository<ShopList,Long> {
    public List<ShopList> findAll();
}
