package controllers.userControllers;

import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Order;
import mainClasses.Requests.RequestAndReply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class UserOrders {

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

    ObservableList<Order> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_ORDER_REPLY");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();

            for (Order order : requestAndReply2.getOrders()) {
                if (order.getNameCustomer().equals(MainController.getNameSave())) {
                    oblist.add(new Order(order.getId(),
                            order.getName(),
                            order.getCost()));
                }
            }
            oos.close();
            ois.close();

        } catch(IOException | ClassNotFoundException ex){
            ex.printStackTrace();
        }


        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_foodName.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        staff_table.setItems(oblist);

    }
}
