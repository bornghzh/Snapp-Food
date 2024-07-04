package com.example.project;

import java.time.LocalDate;
import java.util.*;

public class Restaurant{
    int id;
    int foodTypeNum;
    int foodNumChanging;
    static int foodNum;
    String name;
    String location;
    Set<String> foodTypes;
    ArrayList<Food> menu = new ArrayList<>();
    ArrayList<String> comments = new ArrayList<>();
    ArrayList<Order> activeOrders = new ArrayList<>();
    ArrayList<Order> orderHistory = new ArrayList<>();


    public Restaurant(int id, String name) {
        this.id = id;
        this.name = name;
        this.location = "";
        this.foodTypes = new HashSet<>();
        this.menu = new ArrayList<>();
        this.activeOrders = new ArrayList<>();
        this.orderHistory = new ArrayList<>();
    }

    public void displayComments(){
        for (int i = 0; i < comments.size(); i++) {
            System.out.println((i+1) +". "+comments.get(i));
        }
    }
    public void addFoodType(String foodType) {
        foodTypes.add(foodType);
    }


    public void displayLocation() {
        System.out.println("Location: " + location);
    }
    public void editLocation(String newLocation) {
        location = newLocation;
        System.out.println("Location updated to: " + newLocation);
    }

    public void displayFoodTypes() {
        System.out.println("Food Types:");
        for (String foodType : foodTypes) {
            System.out.println(foodType);
        }
    }



    public void displayOrderStatus(int orderId) {
        boolean exActive = false;
        boolean exHistory = false;
        for (int i = 0; i < activeOrders.size(); i++) {
            if(activeOrders.get(i).orderId == orderId) exActive=true;
        }
        for (int i = 0; i < orderHistory.size(); i++) {
            if(orderHistory.get(i).orderId == orderId) exHistory=true;
        }
        if (exActive) {
            System.out.println("Order ID " + orderId + " is currently active");
        } else if (exHistory) {
            System.out.println("Order ID " + orderId + " has been delivered");
        } else {
            System.out.println("Order ID " + orderId + " not found");
        }
    }

    public void displayOrderDeliveryTime(int orderId) {
        boolean exActive = false;
        boolean exHistory = false;
        int orderPlace=0;
        for (int i = 0; i < activeOrders.size(); i++) {
            if(activeOrders.get(i).orderId == orderId) {exActive=true; orderPlace=i;}
        }
        for (int i = 0; i < orderHistory.size(); i++) {
            if(orderHistory.get(i).orderId == orderId) exHistory=true;
        }
        if (exActive) {
            System.out.println("Estimated delivery time for Order ID " + orderId + ": " + activeOrders.get(orderPlace).deliveryTime+ " minutes later" );
        } else if (exHistory) {
            System.out.println("Order ID " + orderId + " has been delivered");
        } else {
            System.out.println("Order ID " + orderId + " not found");
        }
    }

    public void addOrderToActive(Order orderId) {
        if (activeOrders.contains(orderId)) {
            System.out.println("Order is already active");
        } else if (orderHistory.contains(orderId)) {
            System.out.println("Order has already been delivered");
        } else {
            activeOrders.add(orderId);
            System.out.println("Order ID added to active orders");
        }
    }

    public void moveOrderToHistory(Order orderId) {
        if (activeOrders.contains(orderId)) {
            activeOrders.remove(orderId);
            orderHistory.add(orderId);
            System.out.println("Order moved to order history");
        } else if (orderHistory.contains(orderId)) {
            System.out.println("Order ID has already been delivered");
        } else {
            System.out.println("Order ID not found");
        }
    }

    public void displayActiveOrders() {
        System.out.println("Active Orders:");
        for (int i = 0; i < activeOrders.size(); i++) {
            activeOrders.get(i).displayActiveOrder();
        }
    }

    public void displayOrderHistory() {
        System.out.println("Order History:");
        for (int i = 0; i < orderHistory.size(); i++) {
            orderHistory.get(i).displayOrderHistory();
        }
    }

}
