package controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainClasses.Requests.RequestAndReply;
import mainClasses.Staff;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String name = name_field.getText();
            String surname = surname_field.getText();
            String position = position_field.getText();
            String salary = salary_field.getText();

            Staff staff = new Staff(null, name, surname, position, salary);
            RequestAndReply requestUser = new RequestAndReply("ADD_STAFF_REQUEST", staff);
            oos.writeObject(requestUser);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно добавлено");
            alert.setHeaderText(null);
            alert.setContentText("Сотрудник: " + name_field.getText() + " " + surname + " добавлен!");
            alert.showAndWait();

            enter_button.getScene().getWindow().hide();
            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}