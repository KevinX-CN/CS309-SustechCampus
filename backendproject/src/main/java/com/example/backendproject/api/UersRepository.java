package com.example.backendproject.api;

import com.example.backendproject.domain.ShopList;
import com.example.backendproject.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UersRepository extends JpaRepository<Users,Long> {
    List<Users> findAll();
    Users findByName(String name);

    List<Users> findByType(String type);
}
