package mainClasses;

import java.io.Serializable;

public class Order implements Serializable {
    private Long id;
    private String name;
    private Double cost;
    private String nameCustomer;
    private String addressHome;
    private String contactNumber;

    public Order(Long id, String name, Double cost, String nameCustomer, String addressHome, String contactNumber) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.nameCustomer = nameCustomer;
        this.addressHome = addressHome;
        this.contactNumber = contactNumber;
    }

    public Order(Long id, String name, Double cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getAddressHome() {
        return addressHome;
    }

    public void setAddressHome(String addressHome) {
        this.addressHome = addressHome;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }


    public Order () {
    }


    @Override
    public String toString() {
        return "Order{" +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", nameCustomer='" + nameCustomer + '\'' +
                ", addressHome='" + addressHome + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                '}';
    }
}
