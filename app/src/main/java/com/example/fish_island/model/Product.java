package com.example.fish_island.model;
public class Product {
    int id, category;
    String img, name, product_description,price;
    public Product(int id, String price, String img, String name, String product_description, int category) {
        this.id = id;
        this.price = price;
        this.img = img;
        this.name = name;
        this.product_description = product_description;
        this.category = category;
    }
    public int getCategory() {
        return category;
    }
    public void setCategory(int category) {
        this.category = category;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }
}
