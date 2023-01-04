package com.assignment.gui_a1;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.text.SimpleDateFormat;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ResourceBundle;
import org.jsoup.nodes.Document;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.util.HashMap;
import javafx.scene.Node;
import javafx.fxml.FXML;
import org.jsoup.Jsoup;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import java.net.URL;


public class Page4Controller<E> implements Initializable {

    @FXML private Stage stage;
    @FXML private Scene scene;
    @FXML private Parent root;
    @FXML WebEngine engine;
    @FXML WebView webView;
    @FXML Button button1, button2;
    @Getter @Setter private MS<E> ms;
    @Getter @Setter private SourceDestination sd;
    @Getter @Setter private EndPoints endPoints;
    @FXML Label dateLabel, status, idNum, idVehicle, destination, position;
    @FXML Label label4, label5, label6, label7, label8, label9, label10;
    E p;
    Vehicle v;

    public Page4Controller() throws IOException {
        this.ms = new MS<>();
        this.endPoints = new EndPoints("src/main/java/com/assignment/gui_a1/destinations");
        this.sd = new SourceDestination("Deliverd", "src/main/java/com/assignment/gui_a1/destinations");
    }

    public void goToPage1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("page-1.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setDate() {
        Date date = new Date();
        String strDateFormat = "EEE, dd,MM yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        dateLabel.setText(sdf.format(date).toUpperCase());
    }

    public void setStatus() {
        status.setText(this.sd.getState());
    }

    public void setIdNum(Product p) {
        idNum.setText(String.valueOf(p.getId()));
    }

    public void setIdVehicle(Vehicle v) {
        idVehicle.setText(String.valueOf(v.getId()));
    }


    public void setDestination() {
        destination.setText(String.format("%s to %s", getSd().getSource(), getSd().getDestination()));
    }

    public void setPosition() throws IOException {

        String url = String.format("https://www.distance.to/%s/%s", getSd().getSource(), getSd().getDestination());

        Document doc = Jsoup.connect(url).userAgent("Chrome").get();
        String str = doc.getElementsByTag("span").tagName("value km class").tagName("value km").text();

        String[] array = str.split(":");
        String[] array2 = array[5].split("Driving route");

        position.setText(array2[0]);
    }

    public void display(Label l) {
        getMs().getMap().forEach((key, value) -> l.setText(String.format("%s\t\t\t\t%s%n",key, value)));

    }

    public void deliver(E product, Vehicle vehicle) throws IOException, ClassNotFoundException, InterruptedException {
        Label[] labelArray = new Label[] {label4, label5, label6, label7, label8, label9, label10};

        setDate();
        setDestination();
        setPosition();
        setStatus();

        for (int i = 0; i < getMs().getMap().size(); i++)
            display(labelArray[i]);


        ObjectIO objectIO = new ObjectIO("src/main/java/com/assignment/gui_a1/object");
        getMs().setMap(objectIO.extract());

        for (int i = 0; i < getMs().getMap().size(); i++)
            display(labelArray[i]);

        int count;
        for(count = getMs().getMap().size(); count<7; count++) {
            getMs().proceed(p, 123.44F, "RED", endPoints.getLocationArray(), count, labelArray[count]);
            objectIO.save(getMs().getMap());
        }

        ms.setMap(objectIO.extract());

        Product p = (Product)(product);
        setIdNum(p);
        setIdVehicle(vehicle);

    }

    public void loadMap() {
        engine.load("https://www.google.com/maps/place/"+sd.getDestination());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        engine = webView.getEngine();
        engine.setUserAgent("Chrome");
        loadMap();
    }
}