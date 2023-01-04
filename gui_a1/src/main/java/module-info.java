module com.assignment.gui_a1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires javafx.web;
    requires org.jsoup;


    opens com.assignment.gui_a1 to javafx.fxml;
    exports com.assignment.gui_a1;
}