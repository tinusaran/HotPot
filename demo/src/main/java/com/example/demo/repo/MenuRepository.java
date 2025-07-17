package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByMenuSpeciality(String menuSpeciality);

    // ✅ FIXED: correctly reference the nested field
    List<Menu> findByRestaurant_ResId(int restaurantId);
}


