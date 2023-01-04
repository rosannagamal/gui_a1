package com.assignment.gui_a1;

import lombok.Getter;
import lombok.Setter;

public class Vehicle {
    @Getter @Setter private int id;
    @Getter @Setter private String currentLocation;
    @Getter @Setter private float miles, price;


    public Vehicle(int id, String currentLocation, float miles, float price) {
        this.id = id;
        this.currentLocation = currentLocation;
        this.miles = miles;
        this.price = price;
    }
}
