package controllers.adminControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import mainClasses.News;
import mainClasses.Requests.RequestAndReply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AddNewsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane generalName;

    @FXML
    private TextField generalName_field;

    @FXML
    private TextArea description_field;

    @FXML
    private Button enter_button;

    @FXML
    void enter_btn(ActionEvent event) {


        try {
            Date curTime = new Date();
            DateFormat dtfrm = DateFormat.getDateInstance();
            DateFormat timefrm = DateFormat.getTimeInstance();
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            String heading = generalName_field.getText();
            String description = description_field.getText();
            String dateTime = dtfrm.format(curTime);
            String time = timefrm.format(curTime);

            News news = new News(heading, description, dateTime, time, null);
            RequestAndReply requestUser = new RequestAndReply("ADD_NEWS_REQUEST", news);
            oos.writeObject(requestUser);

            oos.close();
            ois.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Успешно добавлено");
            alert.setHeaderText(null);
            alert.setContentText("Новость добавлена: " + heading);
            alert.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void initialize() {

    }
}
