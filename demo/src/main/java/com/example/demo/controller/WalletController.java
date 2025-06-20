package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Wallet;
import com.example.demo.service.WalletService;

@RestController
public class WalletController {

    @Autowired
    private WalletService walletService;

    @GetMapping("/showWallets")
    public List<Wallet> showAllWallets() {
        return walletService.showAllWallets();
    }

    @PostMapping("/addWallet")
    public String addWallet(@RequestBody Wallet wallet) {
        return walletService.addWallet(wallet);
    }

    @GetMapping("/getCustomerWallets/{cusId}")
    public List<Wallet> getWalletsByCustomer(@PathVariable int cusId) {
        return walletService.getWalletsByCustomerId(cusId);
    }
}

