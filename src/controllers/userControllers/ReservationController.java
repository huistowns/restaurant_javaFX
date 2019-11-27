package controllers.userControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import mainClasses.Database;
import mainClasses.Reservation;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReservationController {
    public static Integer stage = 0;

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
    private TextField name_field;

    @FXML
    private DatePicker dateField;

    @FXML
    private Button enter_basket;

    @FXML
    void dateField(ActionEvent event) {

    }

    @FXML
    void enter_bst(ActionEvent event) {
        Database db = new Database();
        ArrayList<Reservation> listReservation = db.getAllReservation();
        String date = dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        for (int i = 0; i < listReservation.size(); i++) {
            if (stage == 0 && stage.equals(listReservation.get(i).getStage())
                    && name_field.getText().length() <= 0 && date.equals(listReservation.get(i).getDate())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Выберите другое");
                alert.setHeaderText(null);
                alert.setContentText("Данный столик зарезервирован на данное число!, Выберите другое число");
                alert.showAndWait();
            }
        }
        db.addReservation(new Reservation(null,name_field.getText(),date,stage));
    }

    @FXML
    void name_field(ActionEvent event) {

    }

    @FXML
    void stage_1(ActionEvent event) {
        stage = 1;
        System.out.println(stage);
        messageStage(stage);
        String date = dateField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
    void initialize() {
    }


    public  static void messageStage(Integer stole) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Успешно добавлено");
        alert.setHeaderText(null);
        alert.setContentText("Вы выбрали: " + stole + " столик");
        alert.showAndWait();


    }
}
