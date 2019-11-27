package controllers.adminControllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import mainClasses.Database;
import mainClasses.Staff;
import mainClasses.User;

import java.net.URL;
import java.util.ArrayList;
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
        Database db = new Database();
        ArrayList<User> listAdm = db.getAllUsers();
        ArrayList<Staff> listStaff = db.getAllStaff();
        int counterAdm = 0;
        int counterStaff = 0;
        int sumSalary = 0;

        for (int i = 0; i < listAdm.size(); i++) {
            counterAdm++;
        }
        administratorsName.appendText(String.valueOf(counterAdm));

        for (int i = 0; i < listStaff.size(); i++) {
            counterStaff++;
        }
        staffCounter.appendText(String.valueOf(counterStaff));

        for (int i = 0; i < listStaff.size(); i++) {
            sumSalary += Integer.parseInt(listStaff.get(i).getSalary());
        }
        int avrSalary = sumSalary/counterStaff;
        String salaryStaff = Integer.toString(avrSalary);

        averageSalary.appendText(salaryStaff);

    }
}
