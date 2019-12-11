package controllers.userControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import mainClasses.Requests.RequestAndReply;
import mainClasses.Requisites;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea banksRequisites;

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_REQUISITES_REPLY");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();

            for (Requisites requisites: requestAndReply2.getRequisites()) {
                banksRequisites.appendText(requisites.getNameBank() + " | " + requisites.getIdCard() + " | " + requisites.getFullNameOwner() + "\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
