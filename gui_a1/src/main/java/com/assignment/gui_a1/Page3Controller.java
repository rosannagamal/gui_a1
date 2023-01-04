package com.assignment.gui_a1;

import javafx.scene.control.TextField;
import javafx.scene.control.TabPane;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import java.io.FileWriter;

import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;
import lombok.Getter;
import lombok.Setter;


public class Page3Controller {
    @FXML private Stage stage;
    @FXML private Scene scene;
    @FXML private Parent root;
    @FXML private TabPane tabPane;
    @FXML private Tab tab1, tab2, tab3;
    @FXML public Button button1, button2;
    @FXML private TextField ID, currentPosition, color, weight, width, height, price, description, temperature;
    @FXML private TextField extraPrice, miles, pickUp, dropOff, point1, point2, point3, point4;
    @Getter @Setter private FragileProduct product;
    @Getter @Setter private Vehicle vehicle;

    public void switchToPage1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("page-1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToTab1() {
        tabPane.getSelectionModel().select(tab1);
    }

    public void switchToTab2() {
        try{
            this.product = new FragileProduct(Integer.parseInt(ID.getText()), currentPosition.getText(), color.getText(),
                    description.getText(), Float.parseFloat(weight.getText()), Float.parseFloat(height.getText()),
                    Float.parseFloat(width.getText()), Float.parseFloat(price.getText()), Float.parseFloat(temperature.getText()), Float.parseFloat(extraPrice.getText()));

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            tabPane.getSelectionModel().select(tab2);
        }
    }

    public void switchToTab3() {
        try {
            this.vehicle = new Vehicle(Integer.parseInt(ID.getText()), currentPosition.getText(),
                    Float.parseFloat(miles.getText()), Float.parseFloat(price.getText()));

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            tabPane.getSelectionModel().select(tab3);
        }
    }

    public void switchToPage4(ActionEvent event) throws IOException, ClassNotFoundException, InterruptedException {
        try {
            writePoints();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("page-4.fxml"));
            root = loader.load();
            Page4Controller p = loader.getController();
            p.deliver(product, vehicle);
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void writePoints() {
        String data = String.format("%s - %s : %s, %s, %s, %s, %s", pickUp.getText(), dropOff.getText(),
                point1.getText(), point2.getText(), point3.getText(), point4.getText(), point3.getText());

        try {
            FileWriter file = new FileWriter("src/main/java/com/assignment/gui_a1/destinations");
            file.write(data);
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
