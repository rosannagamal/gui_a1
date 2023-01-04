package com.assignment.gui_a1;

import lombok.Getter;
import lombok.Setter;

public class Packaging {
    @Getter
    @Setter
    private float thickness;
    @Getter @Setter private String color;

    public Packaging(float thickness, String color) {
        this.thickness = thickness;
        this.color = color;
    }
}
