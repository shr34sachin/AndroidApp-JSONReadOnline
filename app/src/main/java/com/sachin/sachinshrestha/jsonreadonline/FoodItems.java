package com.sachin.sachinshrestha.jsonreadonline;

/**
 * Created by SachinShrestha on 6/15/2016.
 */
public class FoodItems {
    private int productId;
    private String name;
    private String category;
    private double Price;
    private String Photo;

    //setter method, using Generate
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setPrice(double Price) {
        this.Price = Price;
    }
    public void setPhoto(String Photo) {
        this.Photo = Photo;
    }

    //getter method, using Generate
    public int getProductId() {
        return productId;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public double getPrice() {
        return Price;
    }
    public String getPhoto() {
        return Photo;
    }
}
