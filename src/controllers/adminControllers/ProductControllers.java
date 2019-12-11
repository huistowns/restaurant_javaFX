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
import mainClasses.Food;
import mainClasses.Requests.RequestAndReply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
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
    private Button remove_food;

    @FXML
    void remove_food(ActionEvent event) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/adminView/removeFoodMenu.fxml"));

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
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_FOOD_REPLY");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();

            for (Food food: requestAndReply2.getFoods()) {
                oblist.add(new Food(food.getName(),
                        food.getCost(),
                        food.getId()));
            }

            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        food_table.setItems(oblist);
    }
}
