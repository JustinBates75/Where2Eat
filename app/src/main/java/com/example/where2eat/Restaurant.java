package com.example.where2eat;

public class Restaurant{
    public int Id;
    public String PriceRange;
    public int MIN;
    public int MAX;
    public String Type;
    public String Name;

    public Restaurant(int id, String priceRange, String type, String name, int MIN, int MAX) {
        this.Id = id;
        this.PriceRange = priceRange;
        this.Type = type;
        this.Name = name;
        this.MIN = MIN;
        this.MAX = MAX;
    }
}
