package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Menu;
import com.example.demo.repo.MenuRepository;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> showMenu() {
        return menuRepository.findAll();
    }

    public Menu getById(int id) {
        return menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu item with ID " + id + " not found"));
    }

    public List<Menu> getBySpeciality(String speciality) {
        return menuRepository.findByMenSpeciality(speciality);
    }

    public String deleteById(int id) {
        Menu menu = getById(id);
        menuRepository.delete(menu);
        return "Menu item deleted successfully";
    }
}

