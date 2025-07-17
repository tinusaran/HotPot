// Updated VendorController.java
package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.model.OrderNew;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Vendor;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderNewService;
import com.example.demo.service.RestaurantService;
import com.example.demo.service.VendorService;


@RestController
public class VendorController {

    private final VendorService vendorService;
    private final CustomerService customerService;
    private final RestaurantService restaurantService;
    private final OrderNewService orderNewService;

    public VendorController(VendorService vendorService, CustomerService customerService,
                            RestaurantService restaurantService, OrderNewService orderNewService) {
        this.vendorService = vendorService;
        this.customerService = customerService;
        this.restaurantService = restaurantService;
        this.orderNewService = orderNewService;
    }

    // Existing
    @PostMapping("/addVendor")
    public ResponseEntity<String> addVendor(@RequestBody Vendor vendor) {
        return ResponseEntity.ok(vendorService.addVendor(vendor));
    }

    @GetMapping("/showVendors")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        return ResponseEntity.ok(vendorService.showVendors());
    }

    @GetMapping("/searchVendorByUsername/{username}")
    public ResponseEntity<Vendor> getVendorByUsername(@PathVariable String username) {
        return ResponseEntity.ok(vendorService.getVendorByUsername(username));
    }

    @GetMapping("/searchVendorById/{venId}")
    public ResponseEntity<Vendor> getVendorById(@PathVariable int venId) {
        return ResponseEntity.ok(vendorService.getVendorById(venId));
    }

    // New Admin (Vendor) Endpoints

    @GetMapping("/vendor/customers")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(customerService.showCustomers());
    }

    @GetMapping("/vendor/restaurants")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return ResponseEntity.ok(restaurantService.getAllRestaurants());
    }

    @GetMapping("/vendor/orders")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<List<OrderNew>> getAllOrders() {
        return ResponseEntity.ok(orderNewService.getAllOrders());
    }

    @DeleteMapping("/vendor/customer/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        return ResponseEntity.ok(customerService.deleteCustomer(id));
    }

    @DeleteMapping("/vendor/restaurant/{id}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<String> deleteRestaurant(@PathVariable int id) {
        return ResponseEntity.ok(restaurantService.deleteById(id));
    }

    @GetMapping("/vendor/stats")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        return ResponseEntity.ok(vendorService.getDashboardStats());
    }

    @GetMapping("/showVendorOrders/{venId}")
public ResponseEntity<List<OrderNew>> showVendorOrders(@PathVariable int venId) {
    return ResponseEntity.ok(orderNewService.showVendorOrders(venId));
}



}
