package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Menu;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    // Get all cart items for a specific customer
    List<Cart> findByCustomer(Customer customer);

    // Optional: find cart item by customer and menu item (to update quantity)
    Cart findByCustomerAndMenu(Customer customer, Menu menu);

    // Delete all cart items of a customer (e.g., after placing order)
    void deleteByCustomer(Customer customer);
}
