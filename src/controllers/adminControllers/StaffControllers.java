package controllers.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mainClasses.Database;
import mainClasses.Staff;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class StaffControllers implements Initializable {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Staff> staff_table;

    @FXML
    private TableColumn<Staff, Integer> col_id;

    @FXML
    private TableColumn<Staff, String> col_name;

    @FXML
    private TableColumn<Staff, String> col_surname;

    @FXML
    private TableColumn<Staff, String> col_position;

    @FXML
    private TableColumn<Staff, String> col_salary;

    ObservableList<Staff> oblist = FXCollections.observableArrayList();

    @FXML
    private Button add_staff;

    @FXML
    void add_staff(ActionEvent event) {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/adminView/addStaff.fxml"));

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
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM staff");

            while (rs.next()) {
                oblist.add(new Staff(rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("position"),
                        rs.getString("salary")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        col_position.setCellValueFactory(new PropertyValueFactory<>("position"));
        col_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        staff_table.setItems(oblist);

    }
}
