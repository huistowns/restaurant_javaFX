package mainClasses.Requests;

import mainClasses.*;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestAndReply implements Serializable {
    private String code;
    private String name;
    private Long idEdit;
    private String salary;
    private String position;

    private User user;
    private Staff staff;
    private Food food;
    private Promo promo;
    private Order order;
    private Basket basket;
    private Reservation reservation;
    private News newsRestaurant;
    private Requisites requisite;
    private Consumer consumer;

    private ArrayList<Staff> staffs = null;
    private ArrayList<News> news = null;
    private ArrayList<Order> orders = null;
    private ArrayList<User> users = null;
    private ArrayList<Food> foods = null;
    private ArrayList<Promo> promos = null;
    private ArrayList<Basket> baskets = null;
    private ArrayList<Reservation> reservations = null;
    private ArrayList<Requisites> requisites = null;
    private ArrayList<Consumer> consumers = null;

    private Long id;

    public RequestAndReply(Long idEdit, String salary, String position) {
        this.idEdit = idEdit;
        this.salary = salary;
        this.position = position;
    }

    public RequestAndReply(String code, Long id) {
        this.code = code;
        this.id = id;
    }

    public RequestAndReply(String code, Long id, String salary, String position) {
        this.code = code;
        this.id = id;
        this.salary = salary;
        this.position = position;
    }

    public RequestAndReply(String code, String name) {
        this.code = code;
        this.name = name;
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

    public RequestAndReply(String code, Consumer consumer) {
        this.consumer = consumer;
        this.code = code;
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

    public RequestAndReply (String code, News newsRestaurant) {
        this.code = code;
        this.newsRestaurant = newsRestaurant;
    }

    public String getName() {
        return name;
    }

    public Long getIdEdit() {
        return idEdit;
    }

    public void setIdEdit(Long idEdit) {
        this.idEdit = idEdit;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }


    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RequestAndReply(String code, Promo promo) {
        this.code = code;
        this.promo = promo;
    }

    public RequestAndReply(String code, Requisites requisite) {
        this.code = code;
        this.requisite = requisite;
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

    public Requisites getRequisite() { return requisite; }

    public User getUser() {
        return user;
    }

    public Staff getStaff() { return staff; }

    public Consumer getConsumer() { return consumer; }

    public Reservation getReservation() { return reservation; }

    public Basket getBasket() { return  basket; }

    public Food getFood() { return food; }

    public News getNews() { return newsRestaurant; }

    public Promo getPromo() { return  promo; }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNews(News newsRestaurant) { this.newsRestaurant = newsRestaurant; }

    public void setConsumer (Consumer consumer) {this.consumer = consumer; }

    public void setBasket (Basket basket) { this.basket = basket; }

    public void setRequisite(Requisites requisite) { this.requisite = requisite; }

    public void setReservation(Reservation reservation) { this.reservation = reservation; }

    public void setOrder (Order order) { this.order = order; }

    public void setStaff(Staff staff) { this.staff = staff; }

    public void setFood (Food food) { this.food = food; }

    public void setPromo (Promo promo) { this.promo = promo; }

    public ArrayList<User> getUsers() { return users; }

    public ArrayList<Staff> getStaffs() { return  staffs; }

    public ArrayList<Basket> getBaskets() { return baskets; }

    public ArrayList<Reservation> getReservations() { return  reservations; }

    public ArrayList<Consumer> getConsumers() { return  consumers; }

    public ArrayList<News> getNewsRestaurant() {  return news; }

    public ArrayList<Order> getOrders() { return orders; }

    public ArrayList<Food> getFoods() { return foods; }

    public ArrayList<Promo> getPromos() { return promos; }

    public ArrayList<Requisites> getRequisites() { return  requisites;}

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void setNews(ArrayList<News> news) {this.news = news; }

    public void setOrders(ArrayList<Order> orders) { this.orders = orders;}

    public void setRequisites(ArrayList<Requisites> requisites) {this.requisites = requisites; }

    public void setConsumers(ArrayList<Consumer> consumers) {this.consumers = consumers; }

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
