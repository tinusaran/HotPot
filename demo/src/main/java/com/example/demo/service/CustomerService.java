package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Customer;
import com.example.demo.repo.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public String addCustomer(Customer customer) {
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
        Customer customer = customerRepository.findByCusUsernameAndCusPassword(username, password);
        return customer != null ? 1 : 0;
    }
}
