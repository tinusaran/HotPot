package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String addCustomer(Customer customer) {
        // Hash the password before saving
        customer.setCusPassword(passwordEncoder.encode(customer.getCusPassword()));
        customerRepository.save(customer);
        return "Customer added successfully";
    }

    public List<Customer> showCustomers() {
        return customerRepository.findAll();
    }

    public Customer getByUsername(String username) {
        Customer customer = customerRepository.findByCusUsername(username);
        if (customer == null) {
            throw new ResourceNotFoundException("No customer found with username " + username);
        }
        return customer;
    }

    public int loginCustomer(String username, String password) {
        Customer customer = customerRepository.findByCusUsername(username.trim());
        if (customer != null && passwordEncoder.matches(password.trim(), customer.getCusPassword())) {
            return 1;
        }
        return 0;
    }

    public String deleteCustomer(int id) {
    Customer customer = customerRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Customer with ID " + id + " not found"));
    customerRepository.delete(customer);
    return "Customer deleted successfully.";
}

}
