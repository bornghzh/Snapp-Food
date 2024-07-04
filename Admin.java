package com.example.project;

import java.util.ArrayList;

public class Admin {
    String name;
    String password;

    Admin(String a, String b) {
        name = a.intern();
        password = b.intern();
    }

    ArrayList<Restaurant> restaurants = new ArrayList<>();
}
