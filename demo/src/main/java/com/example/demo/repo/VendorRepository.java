package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    Vendor findByVenUsername(String venUsername);
    // Vendor findByVenUsernameAndVenPassword(String username, String password); // ← Comment or remove this
}
