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

                if (requestUser.getCode().equals("ADD_ADM_REQUEST")) {
                    db.addAdministrator(requestUser.getUser());
                }
                else if (requestUser.getCode().equals("ADD_STAFF_REQUEST")) {
                    db.addStaff(requestUser.getStaff());
                }
                else if (requestUser.getCode().equals("ADD_FOOD_REQUEST")) {
                    db.addFood(requestUser.getFood());
                }

                else if (requestUser.getCode().equals("ADD_NEWS_REQUEST")) {
                    db.addNews(requestUser.getNews());
                }

                else if (requestUser.getCode().equals("ADD_RESERVATION_REQUEST")) {
                    db.addReservation(requestUser.getReservation());
                }

                else if (requestUser.getCode().equals("ADD_ORDER_REQUEST")) {
                    db.addOrder(requestUser.getOrder());
                }

                else if (requestUser.getCode().equals("ADD_REQUISITES_REQUEST")) {
                    db.addRequisites(requestUser.getRequisite());
                }

                else if (requestUser.getCode().equals("ADD_PROMO_REPLY")) {
                    db.addPromo(requestUser.getPromo());
                }
                else if (requestUser.getCode().equals("ADD_BASKET_REQUEST")) {
                    db.addBasket(requestUser.getBasket());
                }

                else if (requestUser.getCode().equals("ADD_CONSUMER_REQUEST")) {
                    db.addConsumer(requestUser.getConsumer());
                }

                else if (requestUser.getCode().equals("VIEW_ADM_REPLY")) {
                        RequestAndReply requestUser2 = new RequestAndReply("ANSWER");
                        requestUser2.setUsers(db.getAllUsers());

                        oos.writeObject(requestUser2);
                    }

                else if (requestUser.getCode().equals("VIEW_BASKET_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_BASKET");
                    requestAndReply2.setBaskets(db.getAllBasket());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_CONSUMER_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_CONSUMER");
                    requestAndReply2.setConsumers(db.getAllConsumer());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_NEWS_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_NEWS");
                    requestAndReply2.setNews(db.getAllNews());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_REQUISITES_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_REQUISIT");
                    requestAndReply2.setRequisites(db.getAllRequisites());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_RESERVATION_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_REQUEST");
                    requestAndReply2.setReservations(db.getAllReservation());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_PROMO_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_PROMO");
                    requestAndReply2.setPromos(db.getAllPromo());

                    oos.writeObject(requestAndReply2);
                }



                else if (requestUser.getCode().equals("VIEW_FOOD_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_FOOD");
                    requestAndReply2.setFoods(db.getAllFoods());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_ORDER_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_ORDER");
                    requestAndReply2.setOrders(db.getAllOrder());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("VIEW_STAFF_REPLY")) {
                    RequestAndReply requestAndReply2 = new RequestAndReply("ANSWER_STAFF");
                    requestAndReply2.setStaff(db.getAllStaff());

                    oos.writeObject(requestAndReply2);
                }

                else if (requestUser.getCode().equals("REMOVE_ORDER_REQUEST")) {
                    if(db.removeFood(requestUser.getId())) {
                        oos.writeObject(new RequestAndReply("SUCCESS_ORDER"));
                    }
                    else {
                        oos.writeObject(new RequestAndReply("NO SUCH ID"));
                    }
                }
                else if (requestUser.getCode().equals("REMOVE_STAFF_REQUEST")) {
                    if (db.removeStaff(requestUser.getId())) {
                        oos.writeObject(new RequestAndReply("SUCCESS_STAFF"));
                    }
                    else {
                        oos.writeObject(new RequestAndReply("NO SUCH ID"));
                    }
                }
                else if (requestUser.getCode().equals("REMOVE_FOOD_MENU_REQUEST")) {
                    if (db.removeFoodMenu(requestUser.getId())) {
                        oos.writeObject(new RequestAndReply("SUCCESS_MENU"));
                    } else {
                        oos.writeObject(new RequestAndReply("NO SUCH ID"));
                    }
                }
                else if (requestUser.getCode().equals("REMOVE_PROMO_REQUEST")) {
                    if (db.removePromo(requestUser.getName())) {
                        oos.writeObject(new RequestAndReply("SUCCESS"));
                    }
                    else {
                        oos.writeObject(new RequestAndReply("no such id"));
                    }
                }

                else if (requestUser.getCode().equals("EDIT_STAFF_REQUEST")) {
                    if (db.editStaff(requestUser.getIdEdit(),requestUser.getSalary(),requestUser.getPosition())) {
                        oos.writeObject(new RequestAndReply("SUCCESS"));
                    }
                    else {
                        oos.writeObject(new RequestAndReply("no such ID"));
                    }
                }
            }
        }
        catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
