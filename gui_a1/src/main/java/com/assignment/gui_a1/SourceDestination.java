package com.assignment.gui_a1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.Getter;
import lombok.Setter;

public class SourceDestination {
    @Getter @Setter private String  state, fileName, fileContent;
    @Getter @Setter private Path path;
    private String source, destination,  points;

    public String[] splitFileContent() {
        String[] destination = fileContent.split(":");
        String first = destination[0];
        return first.split("-");
    }

    public String getSource() {
        setSource();
        return source;
    }

    public void setSource() {
        this.source = splitFileContent()[0];
    }

    public String getDestination() {
        setDestination();
        return destination;
    }

    public void setDestination() {
        this.destination = splitFileContent()[1];
    }

    public String getPoints() {
        setPoints();
        return points;
    }

    public void setPoints() {
        String[] sourceDestination = fileContent.split(":");
        this.points = sourceDestination[1];
    }

    public SourceDestination(String state, String filename) throws IOException {
        this.state = state;
        this.fileName = filename;
        this.path = Path.of(this.fileName);
        this.fileContent = Files.readString(this.path);
    }
}
