package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Wallet;
import com.example.demo.service.WalletService;

@RestController
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping("/showWallets")
    public ResponseEntity<List<Wallet>> showAllWallets() {
        return ResponseEntity.ok(walletService.showAllWallets());
    }

    @PostMapping("/addWallet")
    public ResponseEntity<String> addWallet(@RequestBody Wallet wallet) {
        return ResponseEntity.ok(walletService.addWallet(wallet));
    }

    @GetMapping("/getCustomerWallets/{cusId}")
    public ResponseEntity<List<Wallet>> getWalletsByCustomer(@PathVariable int cusId) {
        return ResponseEntity.ok(walletService.getWalletsByCustomerId(cusId));
    }

    @PostMapping("/topUp")
    public ResponseEntity<Wallet> topUp(@RequestBody Wallet wallet) {
        Wallet updated = walletService.topUpWallet(wallet.getCusId(), wallet.getWalSource(), wallet.getWalAmount());
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/deduct")
    public ResponseEntity<String> deduct(@RequestBody Wallet wallet) {
        boolean success = walletService.deductFromWallet(wallet.getCusId(), wallet.getWalSource(), wallet.getWalAmount());
        if (success) {
            return ResponseEntity.ok("Amount deducted successfully.");
        } else {
            return ResponseEntity.badRequest().body("Insufficient wallet balance.");
        }
    }
}

