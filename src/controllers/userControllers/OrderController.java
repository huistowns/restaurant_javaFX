package controllers.userControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import mainClasses.Basket;
import mainClasses.Food;
import mainClasses.Requests.RequestAndReply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class OrderController {
    public static String nameProduct;
    public static Integer costProduct;
    public static Long idProduct;

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
        idProduct = Long.parseLong(id_field.getText());

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_FOOD");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();


            for (Food food: requestAndReply2.getFoods()) {

                if (food.getId().equals(idProduct)) {
                    nameProduct = food.getName();
                    costProduct = food.getCost();
                    idProduct = food.getId();
                    System.out.println(nameProduct);
                }
                else {
                }
            }



            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Basket basket = new Basket(nameProduct, costProduct, null);
            RequestAndReply requestUser = new RequestAndReply("ADD_BASKET", basket);
            oos.writeObject(requestUser);

            oos.close();
            ois.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно добавлено");
            alert.setHeaderText(null);
            alert.setContentText(" " + nameProduct + " " + costProduct + " добавлен!");
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ObservableList<Food> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_FOOD");
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
