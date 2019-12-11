package controllers.userControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import mainClasses.News;
import mainClasses.Requests.RequestAndReply;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class NewsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea newsArea;

    @FXML
    void initialize() {
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_NEWS_REPLY");
            oos.writeObject(requestAndReply);
            RequestAndReply requestAndReply2 = (RequestAndReply)ois.readObject();

            for (News news: requestAndReply2.getNewsRestaurant()) {
                newsArea.appendText(news.getHeading() + " ------------- [" + news.getDate() + " / " + news.getTime() + "] " + "\n" + "Номер публикации: " + news.getIdNews() + "\n" +
                        news.getDescription() + "\n" + "\n");
                newsArea.appendText("________________________________________________________________________________________" + "\n");
            }

            oos.close();
            ois.close();
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
