package controllers.userControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Basket;
import mainClasses.Order;
import mainClasses.Requests.RequestAndReply;
import mainClasses.SendSMS;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class BasketController {
    private Integer sumProduct;

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
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Long idDelete = Long.parseLong(deleteFood_field.getText());
            oos.writeObject(new RequestAndReply("REMOVE_ORDER", idDelete));
            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();
            System.out.println(requestAndReply2.getCode());


            oos.close();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void add_staff(ActionEvent event) {
        String name = name_field.getText();
        String house = house_field.getText();
        String contact = contact_field.getText();

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_BASKET");
            oos.writeObject(requestAndReply);

            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();



            for (Basket basket: requestAndReply2.getBaskets()) {
                Order order = new Order(null, basket.getName(), basket.getCost(), name, house, contact);
                RequestAndReply requestAddOrder =  new RequestAndReply("ADD_ORDER", order);
                oos.writeObject(requestAddOrder);
            }


            oos.close();
            ois.close();

            SendSMS sendSMS = new SendSMS();
            sendSMS.GetMessage(name, house, contact);

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
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
        Integer productSum = 0;

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_BASKET");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();

            for (Basket basket: requestAndReply2.getBaskets()) {
                oblist.add(new Basket(basket.getName(),
                        basket.getCost(),
                        basket.getId()));
                System.out.println(basket);
            }

            for (Basket basket: requestAndReply2.getBaskets()) {
                productSum += basket.getCost();
            }
            setSumProduct(productSum);

            summa.appendText(String.valueOf(getSumProduct()));

            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));


        staff_table.setItems(oblist);
    }

    public Integer getSumProduct() {
        return sumProduct;
    }

    public void setSumProduct(Integer sumProduct) {
        this.sumProduct = sumProduct;
    }
}
