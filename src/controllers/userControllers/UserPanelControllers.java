package controllers.userControllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserPanelControllers {

    @FXML
    private ResourceBundle resources;

    @FXML
    private BorderPane borderpane;

    @FXML
    private URL location;

    @FXML
    private ImageView close_btn;

    @FXML
    void chat(MouseEvent event) {
        loadUI("../../view/userView/chat");
    }

    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.close();
    }


    @FXML
    void contact(MouseEvent event) {

    }

    @FXML
    void info(MouseEvent event) throws IOException {
        loadUI("../../view/userView/info");
    }

    @FXML
    void menu(MouseEvent event) {
        loadUI("../../view/userView/menu");
    }

    @FXML
    void order(MouseEvent event) {
        loadUI("../../view/userView/order");
    }

    @FXML
    void reservation(MouseEvent event) {
        loadUI("../../view/userView/reservation");
    }


    @FXML
    void staff(MouseEvent event) {

    }

    private void loadUI(String ui) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(UserPanelControllers.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderpane.setCenter(root);
    }

    @FXML
    void initialize() {

    }
}
