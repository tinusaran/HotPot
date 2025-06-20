package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Wallet;
import com.example.demo.repo.WalletRepository;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    public List<Wallet> showAllWallets() {
        return walletRepository.findAll();
    }

    public String addWallet(Wallet wallet) {
        walletRepository.save(wallet);
        return "Wallet added successfully";
    }

    public List<Wallet> getWalletsByCustomerId(int cusId) {
        List<Wallet> wallets = walletRepository.findByCusId(cusId);
        if (wallets.isEmpty()) {
            throw new ResourceNotFoundException("No wallets found for customer ID " + cusId);
        }
        return wallets;
    }
}

