package com.example.project;

import java.util.ArrayList;

public class User {
    String name;
    String password;
    String address;
    double charge;
    Order activeOrder = new Order();

    double orderPrice() {
        double price=0;
        for (int i = 0; i < activeOrder.items.size() ; i++) {
            price+=(activeOrder.items.get(i).price*(1-activeOrder.items.get(i).discountPercent/100));
        }

        return price;
    }

    void clear() {
        activeOrder.items.clear();
        activeOrder.serverRestaurant="".intern();
    }

    User(String a, String b,String c) {
        name = a.intern();
        password = b.intern();
        address = c.intern();
    }

    ArrayList<Order> orderHistory = new ArrayList<>();

    public void displayOrderHistory() {
        System.out.println("Order History:");
        for (int i = 0; i < orderHistory.size(); i++) {
            orderHistory.get(i).displayOrderHistory();
        }
    }
}
