package com.management.models;

import java.util.Objects;
import java.util.UUID;

import com.management.exceptions.InvalidRequestException;
import com.management.models.enums.Category;

public class FoodItem {

    private final String foodItemId;
    private String name;
    private double price;
    private Category category;
    private float rating;

    public FoodItem(String name, double price, Category category, float rating) {
        this.foodItemId = UUID.randomUUID().toString();
        setName(name);
        setPrice(price);
        setCategory(category);
        setRating(rating);
    }

    public String getFoodItemId() {
        return foodItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = Objects.requireNonNull(name, "Name is required").trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new InvalidRequestException("Price can't be negative");
        }
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = Objects.requireNonNull(category, "Category is required");
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

    public boolean isVeg() {
        return this.category == Category.VEG;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof FoodItem)) return false;

        FoodItem other = (FoodItem) obj;

        return name.equalsIgnoreCase(other.name)
                && category == other.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.toLowerCase(), category);
    }

    @Override
    public String toString() {
        return "FoodItem{" +
                "id='" + foodItemId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", rating=" + rating +
                '}';
    }
}