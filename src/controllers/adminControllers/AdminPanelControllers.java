package controllers.adminControllers;

import controllers.userControllers.UserPanelControllers;
import javafx.event.ActionEvent;
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

public class AdminPanelControllers {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderpane;

    @FXML
    private ImageView close_btn;

    @FXML
    void accounting(ActionEvent event) {
        loadUI("../../view/adminView/accounting");
    }
    @FXML
    void close(MouseEvent event) {
        Stage stage = (Stage) borderpane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void info(MouseEvent event) {
        loadUI("../../view/adminView/infoAdmin");
    }

    @FXML
    void products(MouseEvent event) {
            loadUI("../../view/adminView/product");
    }

    @FXML
    void promo(MouseEvent event) {
        loadUI("../../view/adminView/promo");
    }

    @FXML
    void requisites(MouseEvent event) {
        loadUI("../../view/adminView/accounting");
    }

    @FXML
    void orders(MouseEvent event) {
        loadUI("../../view/adminView/order_foods");
    }


    @FXML
    void staff(ActionEvent event) {
        loadUI("../../view/adminView/staff");
    }

    @FXML
    void tax(MouseEvent event) {
        loadUI("../../view/adminView/accounting");
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
