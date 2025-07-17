package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Vendor;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.OrderNewRepository;
import com.example.demo.repo.RestaurantRepository;
import com.example.demo.repo.VendorRepository;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
private CustomerRepository customerRepository;

@Autowired
private RestaurantRepository restaurantRepository;

@Autowired
private OrderNewRepository orderRepo;


    VendorService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public String addVendor(Vendor vendor) {
        vendor.setRole("ROLE_VENDOR"); // ✅ Role assignment centralized
        vendor.setVenPassword(passwordEncoder.encode(vendor.getVenPassword())); // ✅ Secure encoding
        vendorRepo.save(vendor);
        return "Vendor added successfully.";
    }

    public List<Vendor> showVendors() {
        return vendorRepo.findAll();
    }

    public Vendor getVendorByUsername(String username) {
        Vendor vendor = vendorRepo.findByVenUsername(username);
        if (vendor == null) {
            throw new ResourceNotFoundException("Vendor with username '" + username + "' not found.");
        }
        return vendor;
    }

    public int loginVendor(String username, String password) {
        Vendor v = vendorRepo.findByVenUsername(username.trim());
        if (v != null && passwordEncoder.matches(password.trim(), v.getVenPassword())) {
            return 1;
        }
        return 0;
    }

    public Vendor getVendorById(int venId) {
        return vendorRepo.findById(venId)
                .orElseThrow(() -> new ResourceNotFoundException("Vendor with ID " + venId + " not found."));
    }

    public Map<String, Object> getDashboardStats() {
    Map<String, Object> stats = new HashMap<>();
    stats.put("totalCustomers", customerRepository.count());
    stats.put("totalRestaurants", restaurantRepository.count());
    stats.put("totalOrders", orderRepo.count());
    return stats;
}

public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
}

public String deleteCustomerById(int id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
    customerRepository.delete(customer);
    return "Customer deleted successfully.";
}

    public List<Restaurant> getAllRestaurantsForAdmin() {
    return restaurantRepository.findAll();
}

public String deleteRestaurantById(int id) {
    Restaurant res = restaurantRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Restaurant with ID " + id + " not found"));
    restaurantRepository.delete(res);
    return "Restaurant deleted successfully.";
}


}
