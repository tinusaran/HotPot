package com.example.demo.service;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Restaurant;
import com.example.demo.model.Vendor;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.RestaurantRepository;
import com.example.demo.repo.VendorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Attempting to authenticate user: {}", username);
        // First check customer
        Customer customer = customerRepository.findByCusUsername(username);
        if (customer != null) {
            log.debug("Customer found: {}", customer.getCusUsername());
            return new User(customer.getCusUsername(), customer.getCusPassword(),
                    Collections.singleton(() -> "ROLE_CUSTOMER"));
        }

        // Then check vendor
        Vendor vendor = vendorRepository.findByVenUsername(username);
        if (vendor != null) {
            log.debug("Vendor found: {}", vendor.getVenUsername());
            return new User(vendor.getVenUsername(), vendor.getVenPassword(),
                    Collections.singleton(() -> "ROLE_VENDOR"));
        }

        // Then check restaurant
        Restaurant restaurant = restaurantRepository.findByResUsername(username);
        if (restaurant != null) {
            log.debug("Restaurant found: {}", restaurant.getResUsername());
            return new User(restaurant.getResUsername(), restaurant.getResPassword(),
                    Collections.singleton(() -> "ROLE_RESTAURANT"));
        }

        log.warn("User not found with username: {}", username);
        // If not found
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
