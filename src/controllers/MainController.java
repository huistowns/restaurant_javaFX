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
import mainClasses.Consumer;
import mainClasses.Requests.RequestAndReply;
import mainClasses.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController {
    private static String nameSave;
    private static String passwordSave;

    @FXML
    private ResourceBundle resources;

    public static String getNameSave() {
        return nameSave;
    }

    public static void setNameSave(String nameSave) {
        MainController.nameSave = nameSave;
    }

    public static String getPasswordSave() {
        return passwordSave;
    }

    public static void setPasswordSave(String passwordSave) {
        MainController.passwordSave = passwordSave;
    }

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
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String name = name_field.getText();
            String password = password_field.getText();


            RequestAndReply requestUser = new RequestAndReply("VIEW_CONSUMER_REPLY");
            oos.writeObject(requestUser);

            RequestAndReply requestUser2 = (RequestAndReply)ois.readObject();

            for (Consumer c: requestUser2.getConsumers()) {
                System.out.println(c);
                if (name.equals(c.getName()) && password.equals(c.getPassword())) {
                    setNameSave(name);
                    setPasswordSave(password);

                    System.out.println(getNameSave());
                    enter_button.getScene().getWindow().hide();

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
            }

            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void enter_btn(ActionEvent event) {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String nameAdm = name_field.getText();
            String passwordAdm = password_field.getText();


            RequestAndReply requestUser = new RequestAndReply("VIEW_ADM_REPLY");
            oos.writeObject(requestUser);

            RequestAndReply requestUser2 = (RequestAndReply)ois.readObject();

            for (User u: requestUser2.getUsers()) {
                if (nameAdm.equals(u.getName()) && passwordAdm.equals(u.getPassword())) {
                    setNameSave(nameAdm);
                    setPasswordSave(passwordAdm);
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

}
