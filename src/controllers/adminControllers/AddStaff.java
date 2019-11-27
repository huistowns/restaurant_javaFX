package controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainClasses.Database;
import mainClasses.Staff;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddStaff {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_field;

    @FXML
    private TextField surname_field;

    @FXML
    private TextField position_field;

    @FXML
    private TextField salary_field;

    @FXML
    private Button enter_button;

    @FXML
    void enter_btn(ActionEvent event) throws SQLException {
        Database db = new Database();

        db.addStaff(new Staff(null, name_field.getText(), surname_field.getText(),
                position_field.getText(), salary_field.getText()));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Успешно добавлено");
        alert.setHeaderText(null);
        alert.setContentText("Сотрудник " + name_field.getText() + " " + surname_field.getText() + " добавлен!");
        alert.showAndWait();
    }

    @FXML
    void initialize() {

    }
}