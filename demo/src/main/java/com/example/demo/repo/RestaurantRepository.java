package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByResName(String resName);
    Restaurant findByResNameAndResLocation(String resName, String resLocation);
    Restaurant findByResUsername(String resUsername);
}

