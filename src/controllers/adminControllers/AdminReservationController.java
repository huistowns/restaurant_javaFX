package controllers.adminControllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import mainClasses.Requests.RequestAndReply;
import mainClasses.Reservation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminReservationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Reservation> reservation_table;

    @FXML
    private TableColumn<Reservation, Integer> col_id;

    @FXML
    private TableColumn<Reservation, String> col_name;

    @FXML
    private TableColumn<Reservation, String> col_date;

    @FXML
    private TableColumn<Reservation, Integer> col_stage;

    @FXML
    private TableColumn<Reservation, String> col_time;

    @FXML
    private TableColumn<Reservation, Integer> col_ticket;

    ObservableList<Reservation> oblist = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_RESERVATION_REPLY");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();

            for (Reservation reservation: requestAndReply2.getReservations()) {
                oblist.add(new Reservation(reservation.getId(),
                        reservation.getName(),
                        reservation.getDate(),
                        reservation.getStage(),
                        reservation.getTime(),
                        reservation.getTicket()));
            }

            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_stage.setCellValueFactory(new PropertyValueFactory<>("stage"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));
        col_ticket.setCellValueFactory(new PropertyValueFactory<>("ticket"));

        reservation_table.setItems(oblist);
    }
}
