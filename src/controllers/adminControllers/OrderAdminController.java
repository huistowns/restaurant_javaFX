package controllers.adminControllers;

import com.jfoenix.controls.JFXTextArea;
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

public class OrderAdminController {
    static int income = 0;

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

    @FXML
    private JFXTextArea incomeText;

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
                oblist.add(new Order(order.getId(),
                        order.getName(),
                        order.getCost(),
                        order.getNameCustomer(),
                        order.getAddressHome(),
                        order.getContactNumber()));
                income += order.getCost();
            }
                oos.close();
                ois.close();

                incomeText.appendText(String.valueOf(income));

                income = 0;
            } catch(IOException | ClassNotFoundException ex){
                ex.printStackTrace();
            }


            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_foodName.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
            col_buyer.setCellValueFactory(new PropertyValueFactory<>("nameCustomer"));
            col_address.setCellValueFactory(new PropertyValueFactory<>("addressHome"));
            col_contact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));

            staff_table.setItems(oblist);

        System.out.println(incomes());
    }

    public Integer incomes() {
        return income;
    }
}
