package controllers.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Database;
import mainClasses.Promo;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PromoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField percentPromo_field;

    @FXML
    private TextField namePromo_field;

    @FXML
    private Button enter_basket;

    @FXML
    private TableView<Promo> staff_table;

    @FXML
    private TableColumn<Promo, String> col_name;

    @FXML
    private TableColumn<Promo, Integer> col_percent;

    @FXML
    void enter_bst(ActionEvent event) {
        Integer percent = Integer.parseInt(percentPromo_field.getText());
        Database db = new Database();

        db.addPromo(new Promo(namePromo_field.getText(), percent));
    }

    @FXML
    void name_field(ActionEvent event) {

    }

    ObservableList<Promo> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        Database db = new Database();
        ArrayList<Promo> listPromo = db.getAllPromo();
        try {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM promo");

            while (rs.next()) {
                oblist.add(new Promo(rs.getString("name"),
                        rs.getInt("percent")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_percent.setCellValueFactory(new PropertyValueFactory<>("percent"));

        staff_table.setItems(oblist);
    }
}
