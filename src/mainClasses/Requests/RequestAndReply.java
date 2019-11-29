package mainClasses.Requests;

import mainClasses.*;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestAndReply implements Serializable {
    private String code;
    private User user;
    private Staff staff;
    private Food food;
    private Promo promo;
    private Order order;
    private Basket basket;
    private Reservation reservation;

    private ArrayList<Staff> staffs = null;
    private ArrayList<Order> orders = null;
    private ArrayList<User> users = null;
    private ArrayList<Food> foods = null;
    private ArrayList<Promo> promos = null;
    private ArrayList<Basket> baskets = null;
    private ArrayList<Reservation> reservations = null;

    private Long id;

    public RequestAndReply(String code, Long id) {
        this.code = code;
        this.id = id;
    }

    public RequestAndReply(String code) {
        this.code = code;
    }

    public RequestAndReply(String code, User user, Staff staff) {
        this.code = code;
        this.user = user;
    }

    public RequestAndReply(String code, User user) {
        this.code = code;
        this.user = user;
    }

    public RequestAndReply(String code, Reservation reservation) {
        this.reservation = reservation;
        this.code = code;
    }

    public RequestAndReply(String code, Order order) {
        this.order = order;
        this.code = code;
    }

    public RequestAndReply(String code, Basket basket) {
        this.code = code;
        this.basket = basket;
    }

    public RequestAndReply(String code, Promo promo) {
        this.code = code;
        this.promo = promo;
    }

    public RequestAndReply(String code, Staff staff) {
        this.code = code;
        this.staff = staff;
    }
    public RequestAndReply(String code, Food food) {
        this.code = code;
        this.food = food;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestAndReply(String code, User user, ArrayList<User> users) {
        this.code = code;
        this.user = user;
        this.users = users;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Order getOrder() { return order;}

    public User getUser() {
        return user;
    }

    public Staff getStaff() { return staff; }

    public Reservation getReservation() { return reservation; }

    public Basket getBasket() { return  basket; }

    public Food getFood() { return food; }

    public Promo getPromo() { return  promo; }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBasket (Basket basket) { this.basket = basket; }

    public void setReservation(Reservation reservation) { this.reservation = reservation; }

    public void setOrder (Order order) { this.order = order; }

    public void setStaff(Staff staff) { this.staff = staff; }

    public void setFood (Food food) { this.food = food; }

    public void setPromo (Promo promo) { this.promo = promo; }

    public ArrayList<User> getUsers() { return users; }

    public ArrayList<Staff> getStaffs() { return  staffs; }

    public ArrayList<Basket> getBaskets() { return baskets; }

    public ArrayList<Reservation> getReservations() { return  reservations; }

    public ArrayList<Order> getOrders() { return orders; }

    public ArrayList<Food> getFoods() { return foods; }

    public ArrayList<Promo> getPromos() { return promos; }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setOrders(ArrayList<Order> orders) { this.orders = orders;}

    public void setStaff(ArrayList<Staff> staffs) {
        this.staffs = staffs;
    }

    public void setReservations(ArrayList<Reservation> reservations) { this.reservations = reservations; }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public void setPromos(ArrayList<Promo> promos) {
        this.promos = promos;
    }

    public void setBaskets(ArrayList<Basket> baskets) { this.baskets = baskets; }



    @Override
    public String toString() {
        return "(REQUEST AND REPLY HANDLER) CODE = " + "[" + code + "]";
    }
}
