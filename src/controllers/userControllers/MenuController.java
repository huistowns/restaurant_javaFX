package controllers.userControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Food;
import mainClasses.Requests.RequestAndReply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Food> food_table;

    @FXML
    private TableColumn<Food, String> col_name;

    @FXML
    private TableColumn<Food, Integer> col_cost;

    @FXML
    private TableColumn<Food, Long> col_id;

    ObservableList<Food> oblist = FXCollections.observableArrayList();

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