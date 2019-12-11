package controllers.userControllers;

import controllers.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Basket;
import mainClasses.Order;
import mainClasses.Promo;
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
    private TextField promo_field;

    @FXML
    void promo_field(ActionEvent event) {

    }

    @FXML
    private Button add_staff;

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
            oos.writeObject(new RequestAndReply("REMOVE_ORDER_REQUEST", idDelete));
            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();
            System.out.println(requestAndReply2.getCode());


            oos.close();
            ois.close();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно добавлено");
            alert.setHeaderText(null);
            alert.setContentText("ID товара: " +  idDelete  + " удален!");
            alert.showAndWait();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void add_staff(ActionEvent event) {
        String house = house_field.getText();
        String contact = contact_field.getText();

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_BASKET_REPLY");
            oos.writeObject(requestAndReply);

            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();



            if (promo_field.getText().length() <= 0) {
                for (Basket basket : requestAndReply2.getBaskets()) {
                    if (basket.getNameUser().equals(MainController.getNameSave())) {
                        Order order = new Order(null, basket.getName(), Double.valueOf(basket.getCost()), MainController.getNameSave(), house, contact);
                        RequestAndReply requestAddOrder = new RequestAndReply("ADD_ORDER_REQUEST", order);
                        oos.writeObject(requestAddOrder);
                    }
                }
            }
            else {
                Double sumWithPromo = 1.0;
                RequestAndReply requestAndReply3 = new RequestAndReply("VIEW_PROMO_REPLY");
                oos.writeObject(requestAndReply3);

                RequestAndReply requestAndReply4 = (RequestAndReply) ois.readObject();

                for (Promo promo: requestAndReply4.getPromos()) {
                    if (promo_field.getText().equals(promo.getName())) {
                        for (Basket basket: requestAndReply2.getBaskets()) {
                            if (basket.getNameUser().equals(MainController.getNameSave())) {
                                sumWithPromo *= Double.valueOf(basket.getCost()) * (Double.valueOf(promo.getPercent()) / 100);
                                Order order = new Order(null, basket.getName(), sumWithPromo, MainController.getNameSave(), house, contact);
                                RequestAndReply requestAddOrder1 = new RequestAndReply("ADD_ORDER_REQUEST", order);
                                oos.writeObject(requestAddOrder1);

                                sumWithPromo = 1.0;
                            }
                        }
                    }
                }

            }


            oos.close();
            ois.close();

            SendSMS sendSMS = new SendSMS();
//            SendSMS.SendSMSManager(name, house, contact);

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
        System.out.println(promo_field.getText().length());
        Integer productSum = 0;
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_BASKET_REPLY");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();



            for (Basket basket: requestAndReply2.getBaskets()) {
                if (MainController.getNameSave().equals(basket.getNameUser())) {
                    oblist.add(new Basket(MainController.getNameSave(),
                            basket.getName(),
                            basket.getCost(),
                            basket.getId()));
                    productSum += basket.getCost();
                }
            }

            setSumProduct(productSum);
            summa.appendText(String.valueOf(productSum));



            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));

        staff_table.setItems(oblist);
    }

    public Integer getSumProduct() {
        return sumProduct;
    }

    public void setSumProduct(Integer sumProduct) {
        this.sumProduct = sumProduct;
    }
}
