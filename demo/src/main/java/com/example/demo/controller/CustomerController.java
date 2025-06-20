package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Customer;
import com.example.demo.service.CustomerService;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public String addCustomer(@RequestBody Customer customer) {
        return customerService.addCustomer(customer);
    }

    @GetMapping("/showCustomers")
    public List<Customer> showCustomers() {
        return customerService.showCustomers();
    }

    @GetMapping("/searchCustomerByUsername/{username}")
    public Customer searchByUsername(@PathVariable String username) {
        return customerService.getByUsername(username);
    }

    @GetMapping("/loginCustomer/{username}/{password}")
    public String loginCustomer(@PathVariable String username, @PathVariable String password) {
        int result = customerService.loginCustomer(username, password);
        return result == 1 ? "Login Successful" : "Invalid Credentials";
    }
}

