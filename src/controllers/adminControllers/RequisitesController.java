package controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainClasses.Requests.RequestAndReply;
import mainClasses.Requisites;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class RequisitesController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_field;

    @FXML
    private TextField id_field;

    @FXML
    private TextField fullName_field;

    @FXML
    private Button add;

    @FXML
    void addBtn(ActionEvent event) {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String nameBank = name_field.getText();
            String idBank = id_field.getText();
            String fullName = fullName_field.getText();

            Requisites requisites = new Requisites(nameBank, idBank, fullName);
            RequestAndReply requestUser = new RequestAndReply("ADD_REQUISITES_REQUEST", requisites);
            oos.writeObject(requestUser);

            oos.close();
            ois.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно добавлено");
            alert.setHeaderText(null);
            alert.setContentText(nameBank + " " + idBank + " добавлен! ");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
