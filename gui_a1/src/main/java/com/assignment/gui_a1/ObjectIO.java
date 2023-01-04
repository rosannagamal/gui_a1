package com.assignment.gui_a1;

import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.HashMap;

public class ObjectIO {
    @Getter @Setter private String path;

    public ObjectIO(String path) {
        this.path = path;
    }

    public void save(HashMap<String, String> o) {
        try {
            FileOutputStream fileOut = new FileOutputStream(getPath());
            ObjectOutputStream objStream = new ObjectOutputStream(fileOut);
            objStream.writeObject(o);
            objStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HashMap<String, String> extract() throws IOException, ClassNotFoundException {
        HashMap<String, String> map = null;

        try {
            FileInputStream inputStream = new FileInputStream(getPath());
            ObjectInputStream objIS = new ObjectInputStream(inputStream);
            HashMap<String, String> extractedMap = (HashMap<String, String>) objIS.readObject();
            inputStream.close();
            objIS.close();
            map = extractedMap;

        }catch (EOFException e) {
            map = new HashMap<String, String>();
        } return map;

    }
}

