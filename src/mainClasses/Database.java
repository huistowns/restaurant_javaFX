package mainClasses;

import java.sql.*;
import java.util.ArrayList;

public class Database {
    private Connection con;

    public Database() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_db?useUnicode=true&serverTimezone=UTC", "root", "");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addAdministrator(User user) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO administrators_db (id, name, password, telephone) VALUES (NULL, ?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2,user.getPassword());
            ps.setString(3, user.getTelephoneNumber());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addFood(Food food) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO foods (name, cost, id) VALUES (?,?,NULL)");
            ps.setString(1, food.getName());
            ps.setInt(2, food.getCost());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addBasket(Basket basket) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO basket(name, cost, id, nameUser) VALUES (?,?,NULL, ?)");
            ps.setString(1, basket.getName());
            ps.setInt(2, basket.getCost());
            ps.setString(3, basket.getNameUser());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOrder (Order order) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO order_foods(id, name, cost, nameCustomer, addressHome, contactNumber) VALUES (NULL, ?, ?, ?, ?, ?)");
            ps.setString(1, order.getName());
            ps.setDouble(2, order.getCost());
            ps.setString(3, order.getNameCustomer());
            ps.setString(4, order.getAddressHome());
            ps.setString(5, order.getContactNumber());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addRequisites(Requisites requisites) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO requiesites(nameBank, numberBank, fullname) VALUES (?,?,?)");
            ps.setString(1, requisites.getNameBank());
            ps.setString(2, requisites.getIdCard());
            ps.setString(3, requisites.getFullNameOwner());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReservation (Reservation reservation) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO reservation(id, name, date, stage, time, ticket) VALUES (NULL, ?, ?, ?, ?, ?)");
            ps.setString(1, reservation.getName());
            ps.setString(2, reservation.getDate());
            ps.setInt(3, reservation.getStage());
            ps.setString(4, reservation.getTime());
            ps.setInt(5, reservation.getTicket());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPromo (Promo promo) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO promo(name, percent) VALUES (?, ?)");
            ps.setString(1, promo.getName());
            ps.setInt(2, promo.getPercent());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addNews(News news) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO news(heading, description, dateRestaurant, timeRestaurant, id) VALUES (?,?,?,?,NULL)");
            ps.setString(1, news.getHeading());
            ps.setString(2, news.getDescription());
            ps.setString(3, news.getDate());
            ps.setString(4, news.getTime());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addConsumer(Consumer consumer) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO consumer(id, name, password) VALUES (NULL, ?, ?)");
            ps.setString(1, consumer.getName());
            ps.setString(2, consumer.getPassword());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addStaff(Staff staff) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO staff (id, name, surname, position, salary) VALUES (NULL, ?, ?, ?, ?)");
            ps.setString(1, staff.getName());
            ps.setString(2, staff.getSurname());
            ps.setString(3, staff.getPosition());
            ps.setString(4, staff.getSalary());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM administrators_db");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String telephone = rs.getString("telephone");

                list.add(new User(id,name,password,telephone));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ArrayList<Consumer> getAllConsumer() {
        ArrayList<Consumer> listConsumer = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM consumer");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String password = rs.getString("password");

                listConsumer.add(new Consumer(id,name,password));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listConsumer;
    }



    public ArrayList<Staff> getAllStaff() {
        ArrayList<Staff> list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM staff");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                String position = rs.getString("position");
                String salary = rs.getString("salary");

                list.add(new Staff(id,name,surname,position,salary));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<News> getAllNews() {
        ArrayList<News> listNews = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM news");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String heading = rs.getString("heading");
                String description = rs.getString("description");
                String date = rs.getString("dateRestaurant");
                String time = rs.getString("timeRestaurant");
                Long id = rs.getLong("id");

                listNews.add(new News(heading,description,date,time,id));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listNews;
    }

    public ArrayList<Food> getAllFoods() {
        ArrayList<Food> list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM foods");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                Integer cost = rs.getInt("cost");
                Long id = rs.getLong("id");

                list.add(new Food(name,cost,id));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Requisites> getAllRequisites() {
        ArrayList<Requisites> listRequisites = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM requiesites");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("nameBank");
                String numberBank = rs.getString("numberBank");
                String fullname = rs.getString("fullname");

                listRequisites.add(new Requisites(name,numberBank,fullname));
            }

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listRequisites;
    }

    public ArrayList<Basket> getAllBasket() {
        ArrayList<Basket> listBasket = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM basket");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nameUser = rs.getString("nameUser");
                String name = rs.getString("name");
                Integer cost = rs.getInt("cost");
                Long id = rs.getLong("id");

                listBasket.add(new Basket(nameUser,name,cost,id));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBasket;
    }

    public ArrayList<Reservation> getAllReservation() {
        ArrayList<Reservation> list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM reservation");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                Integer stage = rs.getInt("stage");
                String time = rs.getString("time");
                Integer ticket = rs.getInt("ticket");

                list.add(new Reservation(id,name,date,stage, time, ticket));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Order> getAllOrder() {
        ArrayList<Order> listOrder = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM order_foods");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                Double cost = rs.getDouble("cost");
                String nameCustomer = rs.getString("nameCustomer");
                String addressHome = rs.getString("addressHome");
                String contactNumber = rs.getString("contactNumber");
                listOrder.add(new Order(id, name, cost, nameCustomer, addressHome, contactNumber));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOrder;
    }

    public ArrayList<Promo> getAllPromo() {
        ArrayList<Promo> list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM promo");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                Integer percent = rs.getInt("percent");
                list.add(new Promo(name, percent));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurant_db?useUnicode=true&serverTimezone=UTC", "root", "");
        return connection;
    }

    public boolean removeFood(Long id) {
        int number = 0;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM basket WHERE id = ?");
            ps.setInt(1, Math.toIntExact(id));
            number = ps.executeUpdate();
            ps.close();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (number==1);
    }

    public boolean removeFoodMenu(Long id) {
        int number = 0;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM foods WHERE id = ?");
            ps.setInt(1, Math.toIntExact(id));
            number = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (number == 1);
    }



    public boolean removeStaff(Long id) {
        int number = 0;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM staff WHERE id = ?");
            ps.setInt(1, Math.toIntExact(id));
            number = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (number == 1);

    }

    public boolean editStaff(Long id, String salary, String position) {
        int number = 0;
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE staff SET position = ? , salary = ? WHERE id = ?");
            ps.setString(1, position);
            ps.setString(2, salary);
            ps.setLong(3, id);
            number = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return (number == 1);
    }

    public boolean removePromo (String name) {
        int number = 0;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM promo WHERE name = ?");
            ps.setString(1, name);
            number = ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (number == 1);
    }

    public boolean removeNews(Long id) {
        int number = 0;
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM news WHERE id =?");
            ps.setLong(1, id);
            number = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (number == 1);
    }




}