package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Restaurant;
import com.example.demo.repo.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public String addRestaurant(Restaurant res) {
        restaurantRepository.save(res);
        return "Restaurant Added Successfully";
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getById(int id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with ID " + id + " not found"));
    }

    public List<Restaurant> getByName(String name) {
        return restaurantRepository.findByResName(name);
    }

    public Restaurant getByNameAndLocation(String name, String location) {
        Restaurant res = restaurantRepository.findByResNameAndResLocation(name, location);
        if (res == null) {
            throw new ResourceNotFoundException("No restaurant found with name " + name + " at " + location);
        }
        return res;
    }

    public String deleteById(int id) {
        Restaurant res = getById(id); // Reuse getById to throw exception if not found
        restaurantRepository.delete(res);
        return "Restaurant Deleted Successfully";
    }
}

