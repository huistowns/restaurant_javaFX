package controllers.adminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> staff_table;

    @FXML
    private TableColumn<?, ?> col_id;

    @FXML
    private TableColumn<?, ?> col_foodName;

    @FXML
    private TableColumn<?, ?> col_cost;

    @FXML
    private TableColumn<?, ?> col_buyer;

    @FXML
    private TableColumn<?, ?> col_address;

    @FXML
    private TableColumn<?, ?> col_contact;

    @FXML
    void initialize() {

    }
}
