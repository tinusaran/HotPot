package com.example.demo.model;

public class MenuRequestDTO {
    private String itemName;
    private String description;
    private String category;
    private double price;
    private String menuSpeciality;
    private String availabilityTime;
    private String dietaryInfo;
    private String tasteInfo;
    private String nutritionInfo;
    private Integer menuCalories;

    // Getters and setters
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

    public Integer getMenuCalories() {
        return menuCalories;
    }

    public void setMenuCalories(Integer menuCalories) {
        this.menuCalories = menuCalories;
    }
}

