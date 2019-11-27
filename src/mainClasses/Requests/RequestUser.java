package mainClasses.Requests;

import mainClasses.Staff;
import mainClasses.User;

import java.io.Serializable;
import java.util.ArrayList;

public class RequestUser implements Serializable {
    private String code;
    private User user;

    private ArrayList<Staff> staff = null;
    private ArrayList<User> users = null;
    private Long id;

    public RequestUser(String code, Long id) {
        this.code = code;
        this.id = id;
    }

    public RequestUser(String code) {
        this.code = code;
    }

    public RequestUser(String code, User user, Staff staff) {
        this.code = code;
        this.user = user;
    }

    public RequestUser(String code, User user) {
        this.code = code;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RequestUser(String code, User user, ArrayList<User> users) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Staff> getStaff() { return  staff; }

    public void setGoods(ArrayList<User> users) {
        this.users = users;
    }

    public void setStaff(ArrayList<Staff> staff) {
        this.staff = staff;
    }



    @Override
    public String toString() {
        return "RequestUser{" +
                "code='" + code + '\'' +
                ", user=" + user +
                ", users=" + users +
                '}';
    }
}
