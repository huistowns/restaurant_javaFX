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
import mainClasses.Requests.RequestAndReply;
import mainClasses.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
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
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String name = name_field.getText();
            String password = password_field.getText();


            RequestAndReply requestUser = new RequestAndReply("VIEW_ADM_REPLY");
            oos.writeObject(requestUser);

            RequestAndReply requestUser2 = (RequestAndReply)ois.readObject();

            for (User u: requestUser2.getUsers()) {
                if (name.equals(u.getName()) && password.equals(u.getPassword())) {
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
                    break;
                }
            }

            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
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
