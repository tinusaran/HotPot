package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Restaurant;
import com.example.demo.service.RestaurantService;

@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @PostMapping("/addRestaurant")
    public ResponseEntity<String> addRestaurant(@RequestBody Restaurant res) {
        return ResponseEntity.ok(restaurantService.addRestaurant(res));
    }

    @GetMapping("/showRestaurants")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/searchRestaurantById/{id}")
    public ResponseEntity<Restaurant> getRestaurantById(@PathVariable int id) {
        return ResponseEntity.ok(restaurantService.getById(id));
    }

    @GetMapping("/searchRestaurantByName/{name}")
    public ResponseEntity<List<Restaurant>> getRestaurantsByName(@PathVariable String name) {
        return ResponseEntity.ok(restaurantService.getByName(name));
    }

    @GetMapping("/searchRestaurantByNameAndLocation/{name}/{location}")
    public ResponseEntity<Restaurant> getRestaurantByNameAndLocation(
            @PathVariable String name,
            @PathVariable String location) {
        return ResponseEntity.ok(restaurantService.getByNameAndLocation(name, location));
    }

    @GetMapping("/searchRestaurantByCity/{city}")
    public ResponseEntity<List<Restaurant>> getRestaurantsByCity(@PathVariable String city) {
        return ResponseEntity.ok(restaurantService.getByCity(city));
    }

    @GetMapping("/searchRestaurantByUsername/{username}")
    public ResponseEntity<Restaurant> getRestaurantByUsername(@PathVariable String username) {
        return ResponseEntity.ok(restaurantService.getByUsername(username));
    }

    @DeleteMapping("/deleteRestaurant/{id}")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int id) {
        return ResponseEntity.ok(restaurantService.deleteById(id));
    }
}
