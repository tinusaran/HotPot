package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Restaurant;
import com.example.demo.repo.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public String addRestaurant(Restaurant res) {
        // Set role if not provided
        if (res.getRole() == null || res.getRole().isBlank()) {
            res.setRole("restaurant");
        }

        // Hash password before saving
        res.setResPassword(passwordEncoder.encode(res.getResPassword()));

        restaurantRepository.save(res);
        return "Restaurant added successfully.";
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
            throw new ResourceNotFoundException("No restaurant found with name '" + name + "' at '" + location + "'");
        }
        return res;
    }

    public List<Restaurant> getByCity(String city) {
        return restaurantRepository.findByResLocationIgnoreCase(city);
    }

    @Transactional
    public String deleteById(int id) {
        Restaurant res = getById(id);
        restaurantRepository.delete(res);
        return "Restaurant deleted successfully.";
    }

    public Restaurant getByUsername(String username) {
        return restaurantRepository.findByResUsername(username);
    }
}
