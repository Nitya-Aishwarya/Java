package com.management;

import com.management.models.FoodItem;
import com.management.models.Restaurant;
import com.management.models.enums.Category;
import com.management.service.FoodDeliveryManagementService;

public class FoodDeliveryManagement {

    public static void main(String[] args) {

        FoodDeliveryManagementService service =
                new FoodDeliveryManagementService();

        FoodItem biryani = new FoodItem("Biryani", 360.00, Category.NON_VEG, 4.5f);
        FoodItem chickenCurry = new FoodItem("Chicken Curry", 280.00, Category.NON_VEG, 4.3f);
        FoodItem paneerCurry = new FoodItem("Paneer Curry", 320.50, Category.VEG, 4.0f);
        FoodItem vegBiryani = new FoodItem("Veg Biryani", 250.00, Category.VEG, 4.2f);
        FoodItem iceCream = new FoodItem("Ice Cream", 150.00, Category.VEG, 4.8f);
        FoodItem burger = new FoodItem("Burger", 180.00, Category.NON_VEG, 4.1f);
        FoodItem pizza = new FoodItem("Pizza", 450.00, Category.VEG, 4.6f);

        Restaurant yathi = new Restaurant("Yathi", "Kakinada", 4.5f);
        Restaurant spicyHub = new Restaurant("Spicy Hub", "Hyderabad", 4.3f);
        Restaurant foodCorner = new Restaurant("Food Corner", "Vijayawada", 4.2f);

        service.addRestaurant(yathi);
        service.addRestaurant(spicyHub);
        service.addRestaurant(foodCorner);

        System.out.println("=== Restaurants ===");
        System.out.println(service.getRestaurants());

        // ---------------- ADD MENU ITEMS ----------------
        service.addFoodItem(yathi.getRestaurantId(), biryani);
        service.addFoodItem(yathi.getRestaurantId(), paneerCurry);
        service.addFoodItem(yathi.getRestaurantId(), iceCream);

        service.addFoodItem(spicyHub.getRestaurantId(), chickenCurry);
        service.addFoodItem(spicyHub.getRestaurantId(), burger);

        service.addFoodItem(foodCorner.getRestaurantId(), pizza);
        service.addFoodItem(foodCorner.getRestaurantId(), vegBiryani);
        service.addFoodItem(foodCorner.getRestaurantId(), iceCream);

        System.out.println("\n=== Yathi Menu ===");
        System.out.println(service.getMenu(yathi.getRestaurantId()));

        System.out.println("\n=== Spicy Hub Menu ===");
        System.out.println(service.getMenu(spicyHub.getRestaurantId()));

        System.out.println("\n=== Food Corner Menu ===");
        System.out.println(service.getMenu(foodCorner.getRestaurantId()));

        System.out.println("\n=== Search 'biryani' ===");
        System.out.println(service.searchByMenuItems("biryani"));

        System.out.println("\n=== Search 'ice' ===");
        System.out.println(service.searchByMenuItems("ice"));
        
        System.out.println("\n===All Food Items From all Restaurants===");
        System.out.println(service.allFoodItems());
        
        System.out.println("\n===Filtering===");
        System.out.println(service.filterMenuItems(100.00, 300.00));
    }
}