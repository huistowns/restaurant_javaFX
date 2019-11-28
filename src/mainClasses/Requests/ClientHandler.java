package mainClasses.Requests;

import mainClasses.Database;
import mainClasses.Staff;
import mainClasses.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.util.ArrayList;

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
                RequestAndReply requestUser = (RequestAndReply) ois.readObject();
                ArrayList<Staff> listStaff = db.getAllStaff();
                ArrayList<User> listUsers = db.getAllUsers();

                System.out.println(requestUser);

                if (requestUser.getCode().equals("ADD_ADM")) {
                    db.addAdministrator(requestUser.getUser());
                }
                else if (requestUser.getCode().equals("ADD_STAFF")) {
                    db.addStaff(requestUser.getStaff());
                }
                else if (requestUser.getCode().equals("ADD_FOOD")) {
                    db.addFood(requestUser.getFood());
                }

                else if (requestUser.getCode().equals("ADD_ORDER")) {
                    db.addOrder(requestUser.getOrder());
                }

                else if (requestUser.getCode().equals("ADD_PROMO")) {
                    db.addPromo(requestUser.getPromo());
                }
                else if (requestUser.getCode().equals("ADD_BASKET")) {
                    db.addBasket(requestUser.getBasket());
                }

                else if (requestUser.getCode().equals("VIEW_ADM")) {
                        RequestAndReply requestUser2 = new RequestAndReply("ANSWER");
                        requestUser2.setUsers(db.getAllUsers());

                        oos.writeObject(requestUser2);
                    }

                else if (requestUser.getCode().equals("VIEW_BASKET")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_BASKET");
                    requestAndReply2.setBaskets(db.getAllBasket());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_PROMO")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_PROMO");
                    requestAndReply2.setPromos(db.getAllPromo());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_FOOD")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_FOOD");
                    requestAndReply2.setFoods(db.getAllFoods());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_STAFF")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_STAFF");
                    requestAndReply2.setStaff(db.getAllStaff());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("REMOVE_ORDER")) {
                    if(db.removeFood(requestUser.getId())) {
                        oos.writeObject(new RequestAndReply("SUCCESS_ORDER"));
                    }
                    else {
                        oos.writeObject(new RequestAndReply("NO SUCH ID"));
                    }
                }
            }
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
