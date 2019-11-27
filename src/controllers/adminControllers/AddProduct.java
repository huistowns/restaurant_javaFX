package controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainClasses.Database;
import mainClasses.Food;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProduct {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_field;

    @FXML
    private TextField cost_field;

    @FXML
    private Button enter_button;

    @FXML
    void enter_btn(ActionEvent event) {
        Database db = new Database();
        Integer cost = Integer.parseInt(cost_field.getText());

        db.addFood(new Food(name_field.getText(), cost, null));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Успешно добавлено");
        alert.setHeaderText(null);
        alert.setContentText(" " + name_field.getText() + " " + cost_field.getText() + " добавлен!");
        alert.showAndWait();
    }

    @FXML
    void initialize() {


    }
}
