package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Menu;
import com.example.demo.model.MenuRequestDTO;
import com.example.demo.model.Restaurant;
import com.example.demo.repo.MenuRepository;
import com.example.demo.repo.RestaurantRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // ✅ Add a new menu item under a restaurant (used by Restaurant role)
    public String addMenu(Menu menu, int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with ID " + restaurantId + " not found"));

        menu.setRestaurant(restaurant); // Set FK relationship
        menuRepository.save(menu);
        return "Menu item added successfully.";
    }

    // ✅ Add menu for any restaurant (used by Vendor/Admin)
    public String addMenuForVendor(MenuRequestDTO dto, int restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new ResourceNotFoundException("Restaurant with ID " + restaurantId + " not found"));

        Menu menu = new Menu();
        menu.setItemName(dto.getItemName());
        menu.setDescription(dto.getDescription());
        menu.setCategory(dto.getCategory());
        menu.setPrice(dto.getPrice());
        menu.setMenuSpeciality(dto.getMenuSpeciality());
        menu.setAvailabilityTime(dto.getAvailabilityTime());
        menu.setDietaryInfo(dto.getDietaryInfo());
        menu.setTasteInfo(dto.getTasteInfo());
        menu.setNutritionInfo(dto.getNutritionInfo());
        menu.setMenuCalories(dto.getMenuCalories());
        menu.setRestaurant(restaurant);

        menuRepository.save(menu);
        return "Menu item added by admin (vendor) for restaurant ID " + restaurantId;
    }

    public String updateMenuByVendor(int menuId, MenuRequestDTO dto) {
    Menu menu = menuRepository.findById(menuId)
            .orElseThrow(() -> new ResourceNotFoundException("Menu item with ID " + menuId + " not found"));

    menu.setItemName(dto.getItemName());
    menu.setDescription(dto.getDescription());
    menu.setCategory(dto.getCategory());
    menu.setPrice(dto.getPrice());
    menu.setMenuSpeciality(dto.getMenuSpeciality());
    menu.setAvailabilityTime(dto.getAvailabilityTime());
    menu.setDietaryInfo(dto.getDietaryInfo());
    menu.setTasteInfo(dto.getTasteInfo());
    menu.setNutritionInfo(dto.getNutritionInfo());
    menu.setMenuCalories(dto.getMenuCalories());

    menuRepository.save(menu);
    return "Menu item with ID " + menuId + " updated successfully.";
}


    // ✅ Get all menus
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    // ✅ Get a menu item by ID
    public Menu getMenuById(int id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item with ID " + id + " not found"));
    }

    // ✅ Get menus by speciality
    public List<Menu> getMenusBySpeciality(String speciality) {
        return menuRepository.findByMenuSpeciality(speciality);
    }

    // ✅ Get menus by restaurant ID
    public List<Menu> getMenusByRestaurantId(int resId) {
        return menuRepository.findByRestaurant_ResId(resId);
    }

    // ✅ Delete a menu item by ID
    public String deleteMenuById(int id) {
        Menu menu = getMenuById(id);
        menuRepository.delete(menu);
        return "Menu item deleted successfully.";
    }
}
