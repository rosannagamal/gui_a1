package com.assignment.gui_a1;

import lombok.Getter;
import lombok.Setter;

public class Product {
    @Getter @Setter private int id;
    @Getter @Setter private String current_location, color, description;
    @Getter @Setter private float weight, height, width, price;

    public Product(int id, String current_location, String color, String description, float weight, float height,
                   float width, float price) {
        this.id = id;
        this.current_location = current_location;
        this.color = color;
        this.description = description;
        this.weight = weight;
        this.height = height;
        this.width = width;
        this.price = price;
    }

}
