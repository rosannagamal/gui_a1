package com.assignment.gui_a1;

public class FragileProduct extends Product {

    private float temperature;
    private float extraPrice;

    public float getPrice() {
        return super.getPrice() + this.getExtraPrice();
    }

    public FragileProduct(int id, String current_location, String color, String description, float weight, float height, float width, float price, float temperature, float extraPrice) {
        super(id, current_location, color, description, weight, height, width, price);
        this.temperature = temperature;
        this.extraPrice = extraPrice;
    }

    public float getTemperature() {
        return this.temperature;
    }

    public float getExtraPrice() {
        return this.extraPrice;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setExtraPrice(float extraPrice) {
        this.extraPrice = extraPrice;
    }
}
