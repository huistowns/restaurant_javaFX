package controllers.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Order;
import mainClasses.Staff;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderAdminController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Order> staff_table;

    @FXML
    private TableColumn<Order, Long> col_id;

    @FXML
    private TableColumn<Order, String> col_foodName;

    @FXML
    private TableColumn<Order, Integer> col_cost;

    @FXML
    private TableColumn<Order, String> col_buyer;

    @FXML
    private TableColumn<Order, String> col_address;

    @FXML
    private TableColumn<Order, String> col_contact;

    ObservableList<Staff> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {



        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_foodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_buyer.setCellValueFactory(new PropertyValueFactory<>("buyer"));
        col_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        col_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));

//        staff_table.setItems(oblist);

    }
}
