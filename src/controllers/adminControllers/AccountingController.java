package controllers.adminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import mainClasses.Order;
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
        OrderAdminController orderAdminController = new OrderAdminController();
        int incomeFood = 0;

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

            administratorsName.appendText(String.valueOf(counterAdm));

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }

        // обработчик данных сотрудников
        try {
            Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            RequestAndReply requestAndReply = new RequestAndReply("VIEW_STAFF_REPLY");
            RequestAndReply requestAndReply3 = new RequestAndReply("VIEW_ORDER_REPLY");


            oos.writeObject(requestAndReply);
            oos.writeObject(requestAndReply3);

            RequestAndReply requestAndReply2 = (RequestAndReply) ois.readObject();
            RequestAndReply requestAndReply4 = (RequestAndReply) ois.readObject();
            int counterStaff = 0;
            int sumSalary = 0;

            for (Staff staff: requestAndReply2.getStaffs()) {
                counterStaff++;
                sumSalary += Integer.parseInt(staff.getSalary());
            }


            for (Order order : requestAndReply4.getOrders()) {
                incomeFood += order.getCost();
            }

            incomeField.appendText(String.valueOf(incomeFood));
            incomeFood = 0;

            staffCounter.appendText(String.valueOf(counterStaff));
            int avrSalary = sumSalary / counterStaff;
            String salaryStaff = Integer.toString(avrSalary);

            averageSalary.appendText(salaryStaff);

        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }


}
