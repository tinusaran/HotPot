package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Menu;
import com.example.demo.repo.CartRepository;
import com.example.demo.repo.CustomerRepository;
import com.example.demo.repo.MenuRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private MenuRepository menuRepository;

    // Add or update item in cart
    public String addToCart(String username, int menuId, int quantity) {
        Customer customer = customerRepository.findByCusUsername(username);
        Menu menu = menuRepository.findById(menuId).orElse(null);

        if (customer == null || menu == null) {
            return "Invalid customer or menu item.";
        }

        Cart existing = cartRepository.findByCustomerAndMenu(customer, menu);
        if (existing != null) {
            int updatedQuantity = existing.getQuantity() + quantity;
            existing.setQuantity(updatedQuantity);
            existing.setTotalPrice(menu.getPrice() * updatedQuantity); // ✅ Update total price
            cartRepository.save(existing);
            return "Cart updated with additional quantity.";
        }

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setMenu(menu);
        cart.setQuantity(quantity);
        cart.setTotalPrice(menu.getPrice() * quantity); // ✅ Set total price
        // Logging total price for debugging; do not log sensitive data here.
        System.out.println("Saving cart with total price: " + cart.getTotalPrice());
        cartRepository.save(cart);

        return "Item added to cart.";
    }

    // View cart for customer
    public List<Cart> viewCart(String username) {
        Customer customer = customerRepository.findByCusUsername(username);
        return customer != null ? cartRepository.findByCustomer(customer) : null;
    }

    // Update quantity of specific menu item in cart
    public String updateQuantity(String username, int menuId, int newQty) {
        Customer customer = customerRepository.findByCusUsername(username);
        Menu menu = menuRepository.findById(menuId).orElse(null);
        if (customer == null || menu == null) return "Invalid input.";

        Cart cart = cartRepository.findByCustomerAndMenu(customer, menu);
        if (cart == null) return "Item not found in cart.";

        cart.setQuantity(newQty);
        cart.setTotalPrice(menu.getPrice() * newQty); // ✅ Update total price
        cartRepository.save(cart);
        return "Quantity updated.";
    }

    // Remove item from cart
    public String removeFromCart(String username, int menuId) {
        Customer customer = customerRepository.findByCusUsername(username);
        Menu menu = menuRepository.findById(menuId).orElse(null);
        if (customer == null || menu == null) return "Invalid input.";

        Cart cart = cartRepository.findByCustomerAndMenu(customer, menu);
        if (cart != null) {
            cartRepository.delete(cart);
            return "Item removed from cart.";
        }
        return "Item not found in cart.";
    }

    // Clear all items from customer's cart (e.g., after placing an order)
    public String clearCart(String username) {
        Customer customer = customerRepository.findByCusUsername(username);
        if (customer != null) {
            cartRepository.deleteByCustomer(customer);
            return "Cart cleared.";
        }
        return "Invalid customer.";
    }
}
