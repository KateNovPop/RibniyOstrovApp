package com.example.fish_island.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    String name,adress,phone;
    List<Product> order_items = new ArrayList<>();

    public Order() {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.order_items = order_items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Product> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<Product> order_items) {
        this.order_items = order_items;
    }
}
