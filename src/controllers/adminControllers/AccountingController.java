package controllers.adminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import mainClasses.Requests.RequestAndReply;
import mainClasses.Staff;
import mainClasses.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountingController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea administratorsName;

    @FXML
    private TextArea staffCounter;

    @FXML
    private TextArea averageSalary;

    @FXML
    private TextArea incomeField;

    @FXML
    void initialize() {

        // обработчик данных администраторов
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_ADM_REPLY");
            oos.writeObject(requestAndReply);

            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();
            int counterAdm = 0;
            for (User user: requestAndReply2.getUsers()) {
                counterAdm++;
            }

            OrderAdminController orderAdminController = new OrderAdminController();

            administratorsName.appendText(String.valueOf(counterAdm));
            incomeField.appendText(String.valueOf(orderAdminController.incomes()));

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        // обработчик данных сотрудников
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_STAFF_REPLY");

            oos.writeObject(requestAndReply);

            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();
            int counterStaff = 0;
            int sumSalary = 0;

            for (Staff staff: requestAndReply2.getStaffs()) {
                counterStaff++;
                sumSalary += Integer.parseInt(staff.getSalary());
            }

            staffCounter.appendText(String.valueOf(counterStaff));
            int avrSalary = sumSalary / counterStaff;
            String salaryStaff = Integer.toString(avrSalary);

            averageSalary.appendText(salaryStaff);

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

    }
}
