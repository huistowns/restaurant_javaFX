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
            PreparedStatement ps = con.prepareStatement("INSERT INTO basket(name, cost, id) VALUES (?,?,NULL)");
            ps.setString(1, basket.getName());
            ps.setInt(2, basket.getCost());

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
            ps.setInt(2, order.getCost());
            ps.setString(3, order.getNameCustomer());
            ps.setString(4, order.getAddressHome());
            ps.setString(5, order.getContactNumber());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addReservation (Reservation reservation) {
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO reservation(id, name, date, stage) VALUES (NULL, ?, ?, ?)");
            ps.setString(1, reservation.getName());
            ps.setString(2, reservation.getDate());
            ps.setInt(3, reservation.getStage());

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
            PreparedStatement ps = con.prepareStatement("SELECT * from administrators_db");
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

    public ArrayList<Basket> getAllBasket() {
        ArrayList<Basket> list = new ArrayList<>();

        try {
            PreparedStatement ps = con.prepareStatement("SELECT * FROM basket");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                Integer cost = rs.getInt("cost");
                Long id = rs.getLong("id");

                list.add(new Basket(name,cost,id));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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
                list.add(new Reservation(id,name,date,stage));
            }
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
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

    public void removeFood(Long id) {
        try {
            PreparedStatement ps = con.prepareStatement("DELETE FROM basket WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            ps.close();;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}