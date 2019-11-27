package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import mainClasses.Database;
import mainClasses.User;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class MainController {
    public static String nameToSave;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button sign_up_field;

    @FXML
    private Button enter_button;

    @FXML
    private TextField name_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button enter_button_customer;

    @FXML
    void enter_btn_custommer(ActionEvent event) {
        enter_button_customer.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/userView/userPanel.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();

        stage.setScene(new Scene(root));
        stage.show();
    }


    @FXML
    void enter_btn(ActionEvent event) {
        Database db = new Database();
        String name = name_field.getText();
        String password = password_field.getText();


        ArrayList<User> list = db.getAllUsers();
        for (int i = 0; i < list.size(); i++) {
            if (name.equals(list.get(i).getName()) && password.equals(list.get(i).getPassword())) {
                System.out.println("Successful");
                enter_button.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/view/adminView/adminPanel.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();

                stage.setScene(new Scene(root));
                stage.show();
                nameToSave = name;
            }
            else {
                System.out.println("There is not");
            }
        }
    }

    @FXML
    void initialize() {
        sign_up_field.setOnAction(event -> {
            sign_up_field.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/SignUpControllers.fxml"));

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

    public static String getAdministratorInfo(String name, String password) {
        String nameSave = name;
        String passwordSave = password;

        return nameSave + " " + passwordSave ;
    }
}
