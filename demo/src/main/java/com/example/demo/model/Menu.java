package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEN_ID")
    private int menuId;

    @Column(name = "menu_item", nullable = false)
    private String itemName;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "menu_price", nullable = false)
    private double price;

    @Column(name = "menu_speciality")
    private String menuSpeciality;

    @Column(name = "availability_time")
    private String availabilityTime;

    @Column(name = "dietary_info")
    private String dietaryInfo;

    @Column(name = "taste_info")
    private String tasteInfo;

    @Column(name = "nutrition_info")
    private String nutritionInfo;

    @Column(name = "menu_calories", nullable = false)
private Integer menuCalories;


    @ManyToOne
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    // --- Getters and Setters ---

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMenuSpeciality() {
        return menuSpeciality;
    }

    public void setMenuSpeciality(String menuSpeciality) {
        this.menuSpeciality = menuSpeciality;
    }

    public String getAvailabilityTime() {
        return availabilityTime;
    }

    public void setAvailabilityTime(String availabilityTime) {
        this.availabilityTime = availabilityTime;
    }

    public String getDietaryInfo() {
        return dietaryInfo;
    }

    public void setDietaryInfo(String dietaryInfo) {
        this.dietaryInfo = dietaryInfo;
    }

    public String getTasteInfo() {
        return tasteInfo;
    }

    public void setTasteInfo(String tasteInfo) {
        this.tasteInfo = tasteInfo;
    }

    public String getNutritionInfo() {
        return nutritionInfo;
    }

    public void setNutritionInfo(String nutritionInfo) {
        this.nutritionInfo = nutritionInfo;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Integer getMenuCalories() {
    return menuCalories;
}

public void setMenuCalories(Integer menuCalories) {
    this.menuCalories = menuCalories;
}

}
