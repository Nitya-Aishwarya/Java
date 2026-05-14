package com.management.models;

import java.util.*;

import com.management.exceptions.AlreadyExistsException;
import com.management.exceptions.InvalidRequestException;
import com.management.exceptions.NotFoundException;

public class Restaurant {

    private final String restaurantId;
    private String restaurantName;
    private String location;
    private float rating;

    private final Set<FoodItem> menu = new HashSet<>();

    public Restaurant(String restaurantName, String location, float rating) {
        this.restaurantId = UUID.randomUUID().toString();
        setRestaurantName(restaurantName);
        setLocation(location);
        setRating(rating);
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName =
                Objects.requireNonNull(restaurantName, "Restaurant name required").trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location =
                Objects.requireNonNull(location, "Location is required").trim();
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        if (rating < 0 || rating > 5) {
            throw new InvalidRequestException("Rating must be between 0 and 5");
        }
        this.rating = rating;
    }

   

    public void addItem(FoodItem foodItem) {
    	Objects.requireNonNull(foodItem,"FoodItem is required");
    	
    	if (!menu.add(foodItem)) {
    		throw new AlreadyExistsException("Item already exists in menu");
    	}
    }
    
    
    public void removeMenuItem(FoodItem foodItem) {
        Objects.requireNonNull(foodItem, "FoodItem cannot be null");

        if (!menu.remove(foodItem)) {
            throw new NotFoundException("Item not found in menu");
        }
    }

    public Set<FoodItem> getMenu() {
        return Set.copyOf(menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(restaurantId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Restaurant)) return false;
        Restaurant other = (Restaurant) obj;
        return Objects.equals(restaurantId, other.restaurantId);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantId='" + restaurantId + '\'' +
                ", restaurantName='" + restaurantName + '\'' +
                ", location='" + location + '\'' +
                ", rating=" + rating +
                ", menuSize=" + menu.size() +
                '}';
    }
}