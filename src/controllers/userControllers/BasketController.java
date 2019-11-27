package controllers.userControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Basket;
import mainClasses.Database;
import mainClasses.Order;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BasketController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Basket> staff_table;

    @FXML
    private TableColumn<Basket, String> col_name;

    @FXML
    private TableColumn<Basket, Integer> col_cost;

    @FXML
    private TableColumn<Basket, Long> col_id;

    @FXML
    private Button add_staff;

    @FXML
    private TextField name_field;

    @FXML
    private TextField house_field;

    @FXML
    private TextField contact_field;

    @FXML
    private TextArea summa;

    @FXML
    private CheckBox cardCheck;

    @FXML
    private CheckBox cashCheck;


    @FXML
    private TextField deleteFood_field;

    @FXML
    private Button delete_food;

    @FXML
    void delete_food(ActionEvent event) {
        Database db = new Database();
        Long id = Long.parseLong(deleteFood_field.getText());
        System.out.println(id);
        db.removeFood(id);
    }


    @FXML
    void add_staff(ActionEvent event) {
        Database db = new Database();
        ArrayList<Basket> listBasket = db.getAllBasket();

        for(int i = 0; i <= listBasket.size(); i++) {
            if (name_field.getText().length() > 0 && contact_field.getText().length() > 0 &&
            house_field.getText().length() > 0) {
                String nameCustomer = name_field.getText();
                String contact = contact_field.getText();
                String houseContact = house_field.getText();

                db.addOrder(new Order(null, listBasket.get(i).getName(),
                            listBasket.get(i).getCost(), nameCustomer, houseContact, contact));

                db.removeFood((long) i);
                System.out.println("added");
            }
        }

    }

    @FXML
    void cardCheckBox(ActionEvent event) {

    }

    @FXML
    void cashCheckBox(ActionEvent event) {

    }

    @FXML
    private void handleCashBox() {
        if(cashCheck.isSelected()) {
            cardCheck.setSelected(false);
            System.out.println("Cash");
        }
    }

    @FXML
    private void handleCardBox() {
        if(cardCheck.isSelected()) {
            System.out.println("NoCash");
            cashCheck.setSelected(false);
        }
    }

    ObservableList<Basket> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        Database db = new Database();
        ArrayList <Basket> listBasket = db.getAllBasket();
        int sumFood = 0;

        try {
            Connection con = Database.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM basket");

            while (rs.next()) {
                oblist.add(new Basket(rs.getString("name"),
                        rs.getInt("cost"),
                        rs.getLong("id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));


        staff_table.setItems(oblist);


        for (int i = 0; i < listBasket.size(); i++) {
            sumFood += listBasket.get(i).getCost();
        }

        summa.appendText(String.valueOf(sumFood));
    }


}
