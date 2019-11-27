package controllers.userControllers;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mainClasses.Basket;
import mainClasses.Database;
import mainClasses.Food;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Food> food_table;

    @FXML
    private TableColumn<Food, String> col_name;

    @FXML
    private TableColumn<Food, String> col_cost;

    @FXML
    private TableColumn<Food, Long> col_id;

    @FXML
    private TextField id_field;

    @FXML
    private Button enter_button;

    @FXML
    private Button enter_basket;

    @FXML
    void enter_bst(ActionEvent event) {
        Database db = new Database();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/userView/basket.fxml"));
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
    void enter_btn(ActionEvent event) {
        Database db = new Database();
        String name;
        Integer cost;
        Long idFood;
        ArrayList<Food> listFood = db.getAllFoods();
        Integer id = Math.toIntExact(Long.parseLong(id_field.getText()));

        for (int i = 0 ; i < listFood.size(); i++) {
            if (Integer.parseInt(String.valueOf(id)) == listFood.get(i).getId()) {
                name = listFood.get(id).getName();
                cost = listFood.get(id).getCost();
                idFood = listFood.get(id).getId();

                db.addBasket(new Basket(name, cost,idFood));
            }
//            else {
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//
//                alert.setTitle("Ошибка");
//                alert.setHeaderText(null);
//                alert.setContentText("Нету такого продукта");
//                alert.showAndWait();
//            }
        }


    }

    ObservableList<Food> oblist = FXCollections.observableArrayList();

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
