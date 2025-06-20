package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;

@RestController
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping("/addRestaurant")
    public String addRestaurant(@RequestBody Restaurant res) {
        return restaurantService.addRestaurant(res);
    }

    @GetMapping("/showRestaurants")
    public List<Restaurant> showRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @GetMapping("/searchRestaurantById/{id}")
    public Restaurant searchById(@PathVariable int id) {
        return restaurantService.getById(id);
    }

    @GetMapping("/searchRestaurantByName/{name}")
    public List<Restaurant> searchByName(@PathVariable String name) {
        return restaurantService.getByName(name);
    }

    @GetMapping("/searchRestaurantByNameAndLocation/{name}/{location}")
    public Restaurant searchByNameAndLocation(@PathVariable String name, @PathVariable String location) {
        return restaurantService.getByNameAndLocation(name, location);
    }

    @DeleteMapping("/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable int id) {
        return restaurantService.deleteById(id);
    }
}

