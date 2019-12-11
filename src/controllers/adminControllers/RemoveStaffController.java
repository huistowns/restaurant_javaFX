package controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainClasses.Requests.RequestAndReply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class RemoveStaffController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField id_field;

    @FXML
    private TextField description_field;

    @FXML
    private Button enter_button;

    @FXML
    void enter_btn(ActionEvent event) {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Long idDelete = Long.parseLong(id_field.getText());
            String description = description_field.getText();

            oos.writeObject(new RequestAndReply("REMOVE_STAFF_REQUEST", idDelete));
            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();
            System.out.println(requestAndReply2.getCode());



            oos.close();
            ois.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Вы уволили");
            alert.setHeaderText(null);
        alert.setContentText("Вы уволили сотрудника ID: : " + idDelete + "\n" + "Причина: " + description);
            alert.showAndWait();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
