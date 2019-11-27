package mainClasses.Requests;

import mainClasses.Database;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;

public class ClientHandler extends Thread {
    private Socket socket = null;
    private ObjectInputStream ois = null;
    private ObjectOutputStream oos = null;
    private Connection connection = null;

    public ClientHandler(Socket socket, Connection connection) {
        this.socket = socket;
        this.connection = connection;

        try {
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        Database db = new Database();

        try {
            while(true) {
                RequestUser requestUser = (RequestUser) ois.readObject();

                System.out.println(requestUser);

                if (requestUser.getCode().equals("ADD")) {
                    db.addAdministrator(requestUser.getUser());
                }

                else if (requestUser.getCode().equals("VIEW")) {
                    RequestUser requestUser1 = new RequestUser("ANSWER");
                    requestUser1.setStaff(db.getAllStaff());
                }
            }
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
