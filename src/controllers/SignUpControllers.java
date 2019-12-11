package controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainClasses.Consumer;
import mainClasses.Requests.RequestAndReply;
import mainClasses.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SignUpControllers {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button home;

    @FXML
    private TextField name_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private TextField telephone_number_field;

    @FXML
    void registration_btn(ActionEvent event) throws SQLException {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String name = name_field.getText();
            String password = password_field.getText();
            String contact = telephone_number_field.getText();

            User user = new User(null, name, password, contact);
            RequestAndReply requestUser = new RequestAndReply("ADD_ADM_REQUEST", user);
            oos.writeObject(requestUser);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно добавлено");
            alert.setHeaderText(null);
            alert.setContentText("Администратор: " + name_field.getText() + " " + "добавлен!");
            alert.showAndWait();

            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private Button consumer_button;

    @FXML
    void consumer_btn(ActionEvent event) {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String name = name_field.getText();
            String password = password_field.getText();

            Consumer consumer = new Consumer(null, name, password);
            RequestAndReply requestUser = new RequestAndReply("ADD_CONSUMER_REQUEST", consumer);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно добавлено");
            alert.setHeaderText(null);
            alert.setContentText("Пользователь: " + name_field.getText() +  "  " + "добавлен!");
            alert.showAndWait();
            oos.writeObject(requestUser);

            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {


        home.setOnAction(event -> {
            home.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/MainUI.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();

            stage.setScene(new Scene(root));
            stage.show();
        });

    }
}
