package com.example.project;

import java.util.ArrayList;

public class Order {
    ArrayList <Food> items = new ArrayList<>();
    String serverRestaurant;
    int deliveryTime;
    int orderId;

    Order (Order ord){
        for (int i = 0; i < ord.items.size(); i++) {
            items.add(ord.items.get(i));
        }
        serverRestaurant = ord.serverRestaurant.intern();
        deliveryTime = ord.deliveryTime;
        orderId = ord.orderId;
    }
    Order (){
    }
    double Price() {
        double price=0;
        for (int i = 0; i < items.size() ; i++) {
            price+=(items.get(i).price*(1-items.get(i).discountPercent/100));
        }

        return price;
    }

    String string(){
        String w="-";
        for (int i = 0; i < items.size(); i++) {
            w+=items.get(i).name;
            w+="-";
        }

        return w;
    }

    void displayActiveOrder(){
        System.out.println("Order items:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
        System.out.println("approximate delivery time: "+deliveryTime+" minutes later");
    }

    void displayOrderHistory() {
        System.out.println("Order items:");
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i));
        }
    }

}
