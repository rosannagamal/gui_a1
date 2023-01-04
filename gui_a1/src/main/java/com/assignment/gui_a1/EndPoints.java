package com.assignment.gui_a1;

import java.io.IOException;

import lombok.Getter;
import lombok.Setter;


public class EndPoints {

    @Getter @Setter private String fileName;
    private String[] locations;

    public SourceDestination returnObj() throws IOException {
        return new SourceDestination("Delivering", fileName);
    }

    public void setLocation() throws IOException {
        String[] pointsArr = returnObj().getPoints().split(",");
        this.locations = new String[pointsArr.length + 2];
        locations[0] = returnObj().getSource();
        System.arraycopy(pointsArr, 0, locations, 1, pointsArr.length);
        locations[locations.length-1] = returnObj().getDestination();
    }

    public String[] getLocationArray() throws IOException {
        setLocation();
        return this.locations;
    }
    public EndPoints(String fileName) {
        this.fileName = fileName;
    }
}

