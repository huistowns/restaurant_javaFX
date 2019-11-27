package controllers.userControllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import mainClasses.ServerClientChatSystem.ChatAppClient;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void buyerChat(MouseEvent event) {
        // Надо фиксить

        ChatAppClient.launch();
    }

    @FXML
    void hostChat(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }
}
