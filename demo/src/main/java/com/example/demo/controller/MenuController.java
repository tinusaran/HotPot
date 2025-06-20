package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Menu;
import com.example.demo.service.MenuService;

@RestController
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/showMenu")
    public List<Menu> showMenu() {
        return menuService.showMenu();
    }

    @GetMapping("/searchMenuById/{id}")
    public Menu searchById(@PathVariable int id) {
        return menuService.getById(id);
    }

    @GetMapping("/searchMenuBySpeciality/{type}")
    public List<Menu> searchBySpeciality(@PathVariable String type) {
        return menuService.getBySpeciality(type);
    }

    @DeleteMapping("/deleteMenu/{id}")
    public String deleteMenu(@PathVariable int id) {
        return menuService.deleteById(id);
    }
}
