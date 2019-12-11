package controllers.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Promo;
import mainClasses.Requests.RequestAndReply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class PromoController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField percentPromo_field;

    @FXML
    private TextField namePromo_field;

    @FXML
    private Button enter_basket;

    @FXML
    private TableView<Promo> staff_table;

    @FXML
    private TableColumn<Promo, String> col_name;

    @FXML
    private TableColumn<Promo, Integer> col_percent;

    @FXML
    private TextField namePromoToDelete_field;

    @FXML
    void deletePromo(ActionEvent event) {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            String namePromo = namePromoToDelete_field.getText();
            oos.writeObject(new RequestAndReply("REMOVE_PROMO_REQUEST", namePromo));
            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();
            System.out.println(requestAndReply2.getCode());


            oos.close();
            ois.close();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void enter_bst(ActionEvent event) {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String promoName = namePromo_field.getText();
            Integer percent = Integer.parseInt(percentPromo_field.getText());

            Promo promo = new Promo(promoName, percent);
            RequestAndReply requestUser = new RequestAndReply("ADD_PROMO_REPLY", promo);
            oos.writeObject(requestUser);

            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void name_field(ActionEvent event) {

    }

    @FXML
    void enter_bst_delete(ActionEvent event) {
    }

    ObservableList<Promo> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_PROMO_REPLY");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();

            for (Promo promo: requestAndReply2.getPromos()) {
                oblist.add(new Promo(promo.getName(),
                        promo.getPercent()));
            }

            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_percent.setCellValueFactory(new PropertyValueFactory<>("percent"));

        staff_table.setItems(oblist);
    }
}
