package controllers.userControllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import controllers.MainController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import mainClasses.Requests.RequestAndReply;
import mainClasses.Reservation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ReservationController {
    public static Integer stage = 0;
    public static Integer checkOrder = 0;

    @FXML
    void dateFieldq(ActionEvent event) {

    }

    @FXML
    private JFXDatePicker dateFieldq;

    @FXML
    private ResourceBundle resources;



    @FXML
    private URL location;

    @FXML
    private Button stage_1;

    @FXML
    private Button stage_2;

    @FXML
    private Button stage_5;

    @FXML
    private Button stage_4;

    @FXML
    private Button stage_6;

    @FXML
    private Button stage_3;

    @FXML
    private Button stage_7;

    @FXML
    private Button stage_12;

    @FXML
    private Button stage_14;

    @FXML
    private Button stage_11;

    @FXML
    private Button stage_13;

    @FXML
    private Button stage_8;

    @FXML
    private Button stage_10;

    @FXML
    private Button stage_9;
    @FXML
    private TextField time_field;

    @FXML
    private Button enter_basket;

    @FXML
    void dateField(ActionEvent event) {

    }

    @FXML
    void time_field(ActionEvent event) {

    }

    @FXML
    void enter_bst(ActionEvent event) {

        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_RESERVATION_REPLY");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();
            String date = dateFieldq.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String time = timeon.getValue().format(DateTimeFormatter.ofPattern("HH:mm"));
            System.out.println(time);


            for (Reservation reservation: requestAndReply2.getReservations()) {
                if (date.equals(reservation.getDate()) && time.equals(reservation.getTime()) && stage.equals(reservation.getStage())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно добавлено");
                    alert.setHeaderText(null);
                    alert.setContentText("Данный столик занят!");
                    alert.showAndWait();
                    checkOrder = 1;
                    break;
                }
            }

            if (stage == 0 && checkOrder == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка 404");
                alert.setHeaderText(null);
                alert.setContentText("Вы не выбрали столик!");
                alert.showAndWait();
            }
            else if (stage == 0 && date.length() <= 0 && time.length() <= 0 && checkOrder == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка 404");
                alert.setHeaderText(null);
                alert.setContentText("Заполните все поля!");
                alert.showAndWait();
            }



            else if (checkOrder == 0) {
                int minNumber = 100;
                int maxNumber = 5000;
                int randomBilet = minNumber + (int) (Math.random() * maxNumber);

                Reservation reservation = new Reservation(null, MainController.getNameSave(), date, stage, time, randomBilet);
                RequestAndReply requestUser = new RequestAndReply("ADD_RESERVATION_REQUEST", reservation);
                oos.writeObject(requestUser);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Успешно добавлено");
                alert.setHeaderText(null);
                alert.setContentText("Ваша ID, не забудьте предоставить администратору при встрече" + "\n" + "ID: " + randomBilet);
                alert.showAndWait();
            }

            oos.close();
            ois.close();

            checkOrder = 0;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void name_field(ActionEvent event) {

    }

    @FXML
    void stage_1(ActionEvent event) {
        stage = 1;
        System.out.println(stage);
        messageStage(stage);
        String date = dateFieldq.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(date);


    }

    @FXML
    void stage_10(ActionEvent event) {
        stage = 10;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_11(ActionEvent event) {
        stage = 11;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_12(ActionEvent event) {
        stage = 12;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_13(ActionEvent event) {
        stage = 13;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_14(ActionEvent event) {
        stage = 14;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_2(ActionEvent event) {
        stage = 2;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_3(ActionEvent event) {
        stage = 3;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_4(ActionEvent event) {
        stage = 4;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_5(ActionEvent event) {
        stage = 5;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_6(ActionEvent event) {
        stage = 6;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_7(ActionEvent event) {
        stage = 7;
        System.out.println(stage);
        messageStage(stage);

    }

    @FXML
    void stage_8(ActionEvent event) {
        stage = 8;
        System.out.println(stage);
        messageStage(stage);
    }

    @FXML
    void stage_9(ActionEvent event) {
        stage = 9;
        System.out.println(stage);
        messageStage(stage);
    }


    @FXML
    private JFXTimePicker timeon;

    @FXML
    void timeon(ActionEvent event) {

    }



    @FXML
    void initialize() {
        System.out.println(MainController.getNameSave());
    }


    public  static void messageStage(Integer stole) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно добавлено");
        alert.setHeaderText(null);
        alert.setContentText("Вы выбрали: " + stole + " столик");
        alert.showAndWait();


    }
}
