package controllers.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mainClasses.Database;
import mainClasses.Food;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductControllers {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Food> food_table;

    @FXML
    private TableColumn<Food, String> col_name;

    @FXML
    private TableColumn<Food, Long> col_id;

    @FXML
    private TableColumn<Food, Integer> col_cost;

    @FXML
    private Button add_staff;

    ObservableList<Food> oblist = FXCollections.observableArrayList();

    @FXML
    void add_staff(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/adminView/addProduct.fxml"));

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
    void initialize() {
        try {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM foods");

            while (rs.next()) {
                oblist.add(new Food(rs.getString("name"),
                        rs.getInt("cost"),
                        rs.getLong("id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        food_table.setItems(oblist);
    }
}
