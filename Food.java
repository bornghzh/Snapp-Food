package com.example.project;

import java.time.LocalDate;
import java.util.Map;

public class Food {
    int id;
    String name;
    String type;
    double price;
    boolean isActive;
    double discountPercent;
    LocalDate discountExpirationDate;
    Map<Integer, Integer> ratings;
    Map<Integer, String> comments;
    Map<Integer, String> responses;

    Food(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isActive = true;
        this.discountPercent = 0.0;
        this.discountExpirationDate = null;
    }

    public void displayComments() {
        System.out.println("Comments:");
        for (Map.Entry<Integer, String> entry : comments.entrySet()) {
            int commentId = entry.getKey();
            String comment = entry.getValue();
            System.out.println(commentId + ". " + comment);

            if (responses.containsKey(commentId)) {
                String response = responses.get(commentId);
                System.out.println("   - Response: " + response);
            }
        }
    }

    public void addResponse(int commentId, String response) {
        if (!responses.containsKey(commentId)) {
            responses.put(commentId, response);
            System.out.println("Response added for comment with ID " + commentId);
        } else {
            System.out.println("Response already exists for comment with ID " + commentId);
        }
    }

    public void editResponse(int commentId, String newResponse) {
        if (responses.containsKey(commentId)) {
            responses.put(commentId, newResponse);
            System.out.println("Response updated for comment with ID " + commentId);
        } else {
            System.out.println("Cannot edit response. Comment with ID " + commentId + " has no existing response.");
        }
    }
    public void displayRatings() {
        System.out.println("Ratings:");
        for (Map.Entry<Integer, Integer> entry : ratings.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }
    public void addRating(int foodId, int rating) {
        ratings.put(foodId, rating);
        System.out.println("Rating added for food item with ID " + foodId);
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isActive() {
        return isActive;
    }

    public void disable() {
        this.isActive = false;
    }

    public void enable() {
        this.isActive = true;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public LocalDate getDiscountExpirationDate() {
        return discountExpirationDate;
    }

    public void applyDiscount(double discountPercent, LocalDate expirationDate) {
        this.discountPercent = discountPercent;
        this.discountExpirationDate = expirationDate;
    }

    public void removeDiscount() {
        this.discountPercent = 0.0;
        this.discountExpirationDate = null;
    }
}
