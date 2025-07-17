package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Menu;
import com.example.demo.model.MenuRequestDTO;
import com.example.demo.service.MenuService;

@RestController
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping("/restaurant/{restaurantId}/addMenu")
    public ResponseEntity<String> addMenuToRestaurant(
            @PathVariable int restaurantId,
            @RequestBody Menu menu) {

        String result = menuService.addMenu(menu, restaurantId);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/vendor/menu/add/{restaurantId}")
    @PreAuthorize("hasRole('VENDOR')")
    public ResponseEntity<String> addMenuByVendor(
            @RequestBody MenuRequestDTO menuDto,
            @PathVariable int restaurantId) {

        String result = menuService.addMenuForVendor(menuDto, restaurantId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/vendor/menu/update/{menuId}")
@PreAuthorize("hasRole('VENDOR')")
public ResponseEntity<String> updateMenuByVendor(
        @PathVariable int menuId,
        @RequestBody MenuRequestDTO menuDto) {
    String result = menuService.updateMenuByVendor(menuId, menuDto);
    return ResponseEntity.ok(result);
}


    @GetMapping("/showMenu")
    public ResponseEntity<?> showMenu() {
        try {
            List<Menu> menuList = menuService.getAllMenus();
            return ResponseEntity.ok(menuList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Error: " + e.getMessage());
        }
    }

    @GetMapping("/searchMenuById/{id}")
    public ResponseEntity<?> searchById(@PathVariable int id) {
        try {
            Menu menu = menuService.getMenuById(id);
            return ResponseEntity.ok(menu);
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Menu item not found with ID: " + id);
        }
    }

    @GetMapping("/searchMenuBySpeciality/{type}")
    public ResponseEntity<List<Menu>> searchBySpeciality(@PathVariable String type) {
        List<Menu> menus = menuService.getMenusBySpeciality(type);
        return ResponseEntity.ok(menus);
    }


    @GetMapping("/searchMenuByRestaurant/{resId}")
    public ResponseEntity<List<Menu>> getMenuByRestaurant(@PathVariable int resId) {
        List<Menu> menus = menuService.getMenusByRestaurantId(resId);
        return ResponseEntity.ok(menus);
    }

   @DeleteMapping("/vendor/menu/delete/{menuId}")
@PreAuthorize("hasRole('VENDOR')")
public ResponseEntity<String> deleteMenuByVendor(@PathVariable int menuId) {
    String result = menuService.deleteMenuById(menuId);
    return ResponseEntity.ok(result);
}

}
