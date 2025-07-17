package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.OrderNew;
import com.example.demo.service.OrderNewService;

@RestController
public class OrderNewController {

    private final OrderNewService orderService;

    public OrderNewController(OrderNewService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderNew order) {
        String result = orderService.placeOrder(order);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/placeOrderFromCart")
    public ResponseEntity<String> placeOrderFromCart(
            @RequestParam String username,
            @RequestParam String wallet,
            @RequestParam(required = false) String comments) {
        String message = orderService.placeOrderFromCart(username, wallet, comments);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/ordersByCustomer/{custId}")
    public ResponseEntity<List<OrderNew>> getOrdersByCustomer(@PathVariable int custId) {
        List<OrderNew> orders = orderService.showOrdersByCustomer(custId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/ordersByVendor/{venId}")
    public ResponseEntity<List<OrderNew>> getOrdersByVendor(@PathVariable int venId) {
        List<OrderNew> orders = orderService.showOrdersByVendor(venId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/ordersByRestaurant/{resId}")
    public ResponseEntity<List<OrderNew>> getOrdersByRestaurant(@PathVariable int resId) {
        List<OrderNew> orders = orderService.showOrdersByRestaurant(resId);
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/acceptOrReject/{vendorId}/{orderId}/{status}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<String> updateOrderStatus(
            @PathVariable int vendorId,
            @PathVariable int orderId,
            @PathVariable String status) {

        String result = orderService.updateOrderStatus(vendorId, orderId, status);

        if (result.toLowerCase().contains("not") || result.toLowerCase().contains("invalid")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }
}