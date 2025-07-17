package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Menu;
import com.example.demo.model.OrderNew;
import com.example.demo.model.Wallet;
import com.example.demo.repo.CartRepository;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.OrderNewRepository;
import com.example.demo.repo.WalletRepository;

@Service
public class OrderNewService {

    @Autowired
    private OrderNewRepository orderRepo;

    @Autowired
    private WalletRepository walletRepo;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private WalletService walletService;

    @Transactional
    public String placeOrderFromCart(String username, String walletSource, String comments) {
        Customer customer = customerRepository.findByCusUsername(username);
        if (customer == null) return "❌ Customer not found.";

        List<Cart> cartItems = cartRepository.findByCustomer(customer);
        if (cartItems.isEmpty()) return "🛒 Cart is empty.";

        for (Cart cart : cartItems) {
            Menu menu = cart.getMenu();
            if (menu == null) continue;

            double unitPrice = menu.getPrice();
            int quantity = cart.getQuantity();
            double total = unitPrice * quantity;

            OrderNew order = new OrderNew();
            order.setCustId(customer.getCusId());
            order.setVenId(menu.getRestaurant().getResId());
            order.setMenuId(menu.getMenuId());
            order.setWalSource(walletSource);
            order.setQuantityOrdered(quantity);
            order.setBillAmount(total);
            order.setOrderStatus("PENDING");
            order.setComments(comments);

            orderRepo.save(order);
        }

        cartRepository.deleteByCustomer(customer);
        return "✅ Order(s) placed successfully.";
    }

    public String placeOrder(OrderNew order) {
        boolean success = walletService.deductFromWallet(order.getCustId(), order.getWalSource(), order.getBillAmount());

        if (!success) {
            throw new RuntimeException("Insufficient wallet balance.");
        }

        order.setOrderStatus("PENDING");
        orderRepo.save(order);
        return "Order Placed Successfully";
    }

    public List<OrderNew> showOrdersByCustomer(int custId) {
        return orderRepo.findByCustId(custId);
    }

    public List<OrderNew> showOrdersByVendor(int venId) {
        return orderRepo.findByVenId(venId);
    }

    public List<OrderNew> getAllOrders() {
        return orderRepo.findAll();
    }

    public List<OrderNew> showVendorOrders(int venId) {
        return orderRepo.findByVenId(venId);
    }

    public List<OrderNew> showOrdersByRestaurant(int resId) {
        return orderRepo.findByRestaurantId(resId);
    }

    public String updateOrderStatus(int vendorId, int orderId, String status) {
        OrderNew order = orderRepo.findById(orderId)
            .orElseThrow(() -> new ResourceNotFoundException("Order with ID " + orderId + " not found"));

        if (order.getVenId() != vendorId) {
            return "❌ Vendor not authorized to update this order.";
        }

        if (!"PENDING".equalsIgnoreCase(order.getOrderStatus())) {
            return "⚠️ Order already processed. Cannot update.";
        }

        if ("ACCEPTED".equalsIgnoreCase(status)) {
            order.setOrderStatus("ACCEPTED");
            orderRepo.save(order);
            return "✅ Order approved successfully.";
        }

        if ("DENIED".equalsIgnoreCase(status)) {
            order.setOrderStatus("DENIED");
            orderRepo.save(order);

            Wallet wallet = walletRepo.findByCusIdAndWalSource(order.getCustId(), order.getWalSource());
            if (wallet != null) {
                wallet.setWalAmount(wallet.getWalAmount() + order.getBillAmount());
                walletRepo.save(wallet);
                return "❌ Order denied. Amount refunded successfully.";
            } else {
                return "❌ Order denied. Refund failed – wallet not found.";
            }
        }

        return "❌ Invalid status. Use ACCEPTED or DENIED.";
    }
}
