package com.assignment.gui_a1;

import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import java.util.HashMap;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;


public class MS<E> {

    @Getter @Setter private String[] process;
    @Getter @Setter private HashMap<String, String> map;

    public PackageItem<E> addPackage(E item, float thickness, String color) {
        Packaging packageType = new Packaging(thickness, color);
        return new PackageItem<>(packageType, item);
    }

    public String moveLocation(String[] locationArray, int index) {
        return locationArray[index];
    }

    public String updateTime(int i) {
        Date date = new Date();
        long hour = 3600 * 1000;
        Date anotherDate = new Date(date.getTime() + hour * i);
        String strDateFormat = "HH:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        return sdf.format(anotherDate);
    }

    public void display(String key, Label label) {

        String s = String.format("%s\t\t\t\t%s%n",key, getMap().get(key));
        label.setText(s);
    }

    public void proceed(E item, float thickness, String color, String[] locationArray, int count, Label l) {

        {
            try {
                updateDelivery(item, thickness, color, locationArray, count, l);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateDelivery(E item, float thickness, String color, String[] locationArray, int i, Label l) throws InterruptedException {
        if (i == 0) {
            getMap().put(this.getProcess()[0], updateTime(5));
            display(this.getProcess()[0], l);
        }
        else if (i == 1) {
            this.addPackage(item, thickness, color);
            getMap().put(this.getProcess()[1], updateTime(10));
            display(this.getProcess()[1], l);
        }
        else if (i == 2) {
            getMap().put(this.getProcess()[2] + moveLocation(locationArray, 0), updateTime(15));
            display(this.getProcess()[2] + moveLocation(locationArray, 0), l);
        }
        else if (i == 3 || i == 4 || i == 5) {
            getMap().put(this.getProcess()[3] + moveLocation(locationArray, i-1), updateTime(5 * i));
            display(this.getProcess()[3] + moveLocation(locationArray, i-1), l);
        }
        else if (i == 6) {
            getMap().put(this.getProcess()[4] + moveLocation(locationArray, (1)), updateTime(30));
            display(this.getProcess()[4] + moveLocation(locationArray, (1)), l);
        }
    }
    public MS() {
        this.map = new HashMap<>();
        this.process = new String[]{"Product Selected", "Product Packed", "Delivering from ", "Current Location ", "Product has reached the final destination "};
    }
}