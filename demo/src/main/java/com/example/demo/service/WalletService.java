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
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet cannot be null.");
        }
        walletRepository.save(wallet);
        return "Wallet added successfully.";
    }

    public List<Wallet> getWalletsByCustomerId(int cusId) {
        List<Wallet> wallets = walletRepository.findByCusId(cusId);
        if (wallets.isEmpty()) {
            throw new ResourceNotFoundException("No wallets found for customer ID " + cusId);
        }
        return wallets;
    }

    public Wallet getWalletByCustomerIdAndSource(int cusId, String source) {
        Wallet wallet = walletRepository.findByCusIdAndWalSource(cusId, source);
        if (wallet == null) {
            throw new ResourceNotFoundException("Wallet with source '" + source + "' for customer ID " + cusId + " not found.");
        }
        return wallet;
    }

    public Wallet topUpWallet(int cusId, String source, double amount) {
        Wallet wallet = walletRepository.findByCusIdAndWalSource(cusId, source);
        if (wallet == null) {
            wallet = new Wallet();
            wallet.setCusId(cusId);
            wallet.setWalSource(source);
            wallet.setWalAmount(amount);
        } else {
            wallet.setWalAmount(wallet.getWalAmount() + amount);
        }
        return walletRepository.save(wallet);
    }

    public boolean deductFromWallet(int cusId, String source, double amount) {
        Wallet wallet = walletRepository.findByCusIdAndWalSource(cusId, source);
        if (wallet != null && wallet.getWalAmount() >= amount) {
            wallet.setWalAmount(wallet.getWalAmount() - amount);
            walletRepository.save(wallet);
            return true;
        }
        return false; // Insufficient balance
    }
}

