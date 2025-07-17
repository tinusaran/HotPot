package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Cart;
import com.example.demo.service.CartService;
import com.example.demo.util.JwtUtil;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private JwtUtil jwtUtil;

    // Add to cart
    @PostMapping("/add")
    public ResponseEntity<String> addToCart(
            @RequestHeader("Authorization") String token,
            @RequestParam int menuId,
            @RequestParam int quantity
    ) {
        String username = jwtUtil.extractUsername(token.substring(7));
        String message = cartService.addToCart(username, menuId, quantity);
        return ResponseEntity.ok(message);
    }

    // View cart
    @GetMapping("/view")
    public ResponseEntity<List<Cart>> viewCart(
            @RequestHeader("Authorization") String token
    ) {
        String username = jwtUtil.extractUsername(token.substring(7));
        List<Cart> cartItems = cartService.viewCart(username);
        return ResponseEntity.ok(cartItems);
    }

    // Update quantity
    @PutMapping("/update")
    public ResponseEntity<String> updateQuantity(
            @RequestHeader("Authorization") String token,
            @RequestParam int menuId,
            @RequestParam int quantity
    ) {
        String username = jwtUtil.extractUsername(token.substring(7));
        String message = cartService.updateQuantity(username, menuId, quantity);
        return ResponseEntity.ok(message);
    }

    // Remove an item
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeFromCart(
            @RequestHeader("Authorization") String token,
            @RequestParam int menuId
    ) {
        String username = jwtUtil.extractUsername(token.substring(7));
        String message = cartService.removeFromCart(username, menuId);
        return ResponseEntity.ok(message);
    }

    // Clear entire cart
    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart(
            @RequestHeader("Authorization") String token
    ) {
        String username = jwtUtil.extractUsername(token.substring(7));
        String message = cartService.clearCart(username);
        return ResponseEntity.ok(message);
    }
}
