package mainClasses;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String name;
    private String password;
    private String telephoneNumber;

    public User(Long id,String name, String password, String telephoneNumber) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.telephoneNumber = telephoneNumber;
    }

    public User() {

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", telephoneNumber='" + telephoneNumber + '\'' +
                '}';
    }
}
