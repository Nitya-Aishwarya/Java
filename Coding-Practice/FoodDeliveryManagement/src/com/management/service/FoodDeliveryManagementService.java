package com.management.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.management.exceptions.AlreadyExistsException;
import com.management.exceptions.InvalidRequestException;
import com.management.exceptions.NotFoundException;
import com.management.models.FoodItem;
import com.management.models.Restaurant;

public class FoodDeliveryManagementService {

    private final Map<String, Restaurant> restaurants = new HashMap<>();

    
    public List<Restaurant>getRestaurants(){
		return restaurants.values().stream().toList();
    	
    }
    public void addRestaurant(Restaurant restaurant) {
        Objects.requireNonNull(restaurant, "Restaurant cannot be null");

        boolean exists = restaurants.values().stream()
                .anyMatch(r ->
                        r.getRestaurantName().equalsIgnoreCase(restaurant.getRestaurantName()) &&
                        r.getLocation().equalsIgnoreCase(restaurant.getLocation())
                );

        if (exists) {
            throw new AlreadyExistsException("Restaurant already exists");
        }

        restaurants.put(restaurant.getRestaurantId(), restaurant);
    }

    public Restaurant getRestaurant(String restaurantId) {
        Restaurant restaurant = restaurants.get(restaurantId);

        if (restaurant == null) {
            throw new NotFoundException("Restaurant not found");
        }

        return restaurant;
    }

    public void addFoodItem(String restaurantId, FoodItem foodItem) {
        Restaurant restaurant = getRestaurant(restaurantId);
        if(restaurant.getMenu().stream().anyMatch(item->item.equals(foodItem))) {
        	throw new AlreadyExistsException("Food item already added");
        };
        restaurant.addItem(foodItem);
    }

    public void removeFoodItem(String restaurantId, FoodItem foodItem) {
        Restaurant restaurant = getRestaurant(restaurantId);
        if(restaurant.getMenu().stream().noneMatch(item->item.equals(foodItem))) {
        	throw new InvalidRequestException("Food item not existed");
        }
        restaurant.removeMenuItem(foodItem);
    }

    public Set<FoodItem> getMenu(String restaurantId) {
        return getRestaurant(restaurantId).getMenu();
    }
    
    public List<String> searchByMenuItems(String searchedWord) {
        if (searchedWord == null || searchedWord.trim().isEmpty()) {
            return List.of();
        }

        String keyword = searchedWord.toLowerCase();

        return restaurants.values().stream()
                .flatMap(r -> r.getMenu().stream())
                .filter(item -> item.getName().toLowerCase().contains(keyword))
                .map(FoodItem::getName)
                .toList();
    }
    
    public List<String>allFoodItems(){
		return restaurants.values()
						.stream()
						.flatMap(restaurant->restaurant.getMenu().stream())
						.map(FoodItem::getName).toList();

    }
    
//    Filter menu items:
//
//    	price between X and Y
//    	rating above 4
//    	category = VEG
    
    public List<String> filterMenuItems(Double range1,Double range2){
		return restaurants.values()
				.stream()
				.flatMap(restaurant->restaurant.getMenu().stream())
				.filter(item->item.getPrice()>range1 &&item.getPrice()<range2
						&& item.getRating()>4 && item.getCategory()==com.management.models.enums.Category.VEG)
				.map(FoodItem::getName).toList();
    	
    }
}